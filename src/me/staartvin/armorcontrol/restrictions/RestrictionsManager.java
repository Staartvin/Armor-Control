package me.staartvin.armorcontrol.restrictions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.message;
import me.staartvin.armorcontrol.requirements.Requirement;
import me.staartvin.armorcontrol.requirements.RequirementType;
import me.staartvin.plugins.pluginlibrary.Library;
import net.md_5.bungee.api.ChatColor;

public class RestrictionsManager {

	private ArmorControl plugin;

	private List<Restriction> restrictions = new ArrayList<Restriction>();

	public static enum actionType {
		LEFT_CLICK_AIR, RIGHT_CLICK_AIR, LEFT_CLICK_BLOCK, RIGHT_CLICK_BLOCK, LEFT_CLICK_MOB, RIGHT_CLICK_MOB, WEAR, LEFT_CLICK_PLAYER, RIGHT_CLICK_PLAYER, SHOOT_BOW
	};

	public RestrictionsManager(ArmorControl instance) {
		plugin = instance;
	}

	public void loadRestrictions() {
		// Clear res.
		restrictions.clear();

		List<String> items = plugin.getConfigHandler().getRestrictedItems();

		int count = 0;

		// For every name included in the config, do some things
		for (String item : items) {
			Restriction r = new Restriction();

			int itemID = plugin.getConfigHandler().getItemID(item);
			int dataValue = plugin.getConfigHandler().getDataValue(item);

			if (itemID < 0) {
				continue;
			}

			@SuppressWarnings("deprecation")
			ItemStack itemStack = new ItemStack(itemID, 1, (short) dataValue);

			// Set item id
			r.setItemID(itemID);

			// For every action, store their requirements.
			for (actionType action : actionType.values()) {

				List<RequirementType> reqTypes = plugin.getConfigHandler().getRequirementStrings(item, action);

				List<Requirement> requirements = new ArrayList<Requirement>();

				// Get all requirements
				for (RequirementType reqType : reqTypes) {
					Requirement requirement = plugin.getRequirementManager().createRequirement(reqType);

					if (requirement != null) {

						// Set the description of the requirement (from the
						// restrictions.yml)
						requirement.setDescription(plugin.getConfigHandler().getDescription(item, action, reqType));

						// Setup this requirement with the proper options.
						boolean result = requirement
								.setOptions(plugin.getConfigHandler().getRequirementValues(itemStack, action, reqType));

						if (!result) {
							plugin.getLogger().warning("The requirement value of '" + reqType.toString()
									+ "' for the item '" + item + "' is not valid!");
							continue; // Skip this requirement.
						}

						requirements.add(requirement);
					} else {
						plugin.getLogger()
								.warning("Requirement '" + reqType + "' for item '" + item + "' is not valid!");
					}
				}

				r.setRequirements(action, requirements);
			}

			if (dataValue >= 0) {
				r.setDataValue(dataValue);
			}

			List<String> disabledWorlds = plugin.getConfigHandler().getDisabledWorlds(itemStack);

			// Add disabled worlds
			for (String world : disabledWorlds) {
				r.addDisabledWorld(world);
			}

			// Save this restriction
			restrictions.add(r);

			count++;
		}

		plugin.debugMessage("Loaded " + count + " restrictions!");
	}

	// @SuppressWarnings("deprecation")
	// public int getRequiredLevel(ItemStack item, actionType type) {
	//
	// int dataValue = item.getDurability();
	//
	// if (shouldIgnoreDataValue(item) || item.getDurability() == 0) {
	// dataValue = -1;
	// }
	//
	// return this.getRequiredLevel(item.getTypeId(), dataValue, type);
	// }

	public void checkArmor(Player player) {
		// Checks if player is wearing restricted items
		PlayerInventory inv = player.getInventory();

		// We do not have to check on this world.
		if (plugin.getAPI().isDisabledWorld(player.getLocation().getWorld().getName())) {
			return;
		}

		// Ignore creative?
		if (plugin.getConfigHandler().shouldIgnoreCreative()) {
			if (player.getGameMode().equals(GameMode.CREATIVE))
				return;
		}

		@SuppressWarnings("unused")
		int itemID = 0, dataValue = 0;
		ItemStack item;

		actionType type = actionType.WEAR;

		// Slot 36 to 39 are armor slots.
		for (int i = 36; i < 40; i++) {
			item = inv.getItem(i);

			if (item == null)
				continue;

			// itemID = item.getTypeId();
			// dataValue = item.getDurability();

			if (dataValue == 0) {
				dataValue = -1;
			}

			Restriction r = this.getRestriction(item);

			// No restriction for this item found.
			if (r == null)
				continue;

			List<Requirement> failed = r.getFailedRequirements(player, type);

			// Cannot wear this item
			if (!failed.isEmpty()) {
				// Remove it from slot and give it back.
				player.getInventory().setItem(i, null);

				this.giveItem(player, item);

				plugin.getMessageHandler().sendMessage(player,
						plugin.getConfigHandler().getMessage(message.NOT_ALLOWED_TO_WEAR_ARMOR));

				// Show what the player still has to do to wear this armor.
				for (Requirement failedReq : failed) {
					player.sendMessage(ChatColor.RED + "- " + failedReq.getDescription());
				}
			}
		}
	}

	public void giveItem(Player player, ItemStack item) {
		Inventory inv = player.getInventory();

		// Try to search for a free spot in their inv.
		if (inv.firstEmpty() >= 0) {
			inv.addItem(item);
		} else {
			// Drop item on the ground.
			player.getWorld().dropItem(player.getLocation(), item);
		}
	}

	public boolean shouldIgnoreDataValue(ItemStack item) {
		// Some items have their durability as 'real' durability.
		// Tools have for example this behavior.
		// Since tools, weapons, bows and armor use durability we need to ignore
		// that durability value
		// Since these items all have a stack size of 1, we can safely assume
		// that those durability values can be ignored.
		return item.getMaxStackSize() == 1;
	}

	@SuppressWarnings("deprecation")
	public Restriction getRestriction(ItemStack item) {

		int dataValue = item.getDurability();

		if (shouldIgnoreDataValue(item) || item.getDurability() == 0) {
			dataValue = -1;
		}

		for (Restriction r : this.restrictions) {
			if (r.getItemID() != item.getTypeId())
				continue;

			if (r.hasEqualDataValue(dataValue))
				;

			return r;
		}

		return null;
	}

	public void checkLibraries() {
		// Check if all libraries that are required are loaded.

		for (Restriction r : this.restrictions) {
			for (actionType type : actionType.values()) {
				for (Requirement req : r.getRequirements(type)) {
					if (req == null)
						continue;

					List<Library> libs = req.getRequiredLibraries();
					
					if (libs == null || libs.isEmpty()) continue;
					
					for (Library lib : libs) {

						if (lib == null)
							continue;

						if (!plugin.getPluginLibraryHook().isLoaded(lib)) {
							plugin.getLogger().warning("For requirement '" + req.getClass().getSimpleName() + "', Armor Control needs '"
									+ lib.getPluginName() + "' but it is not loaded!");
						}
					}
				}
			}
		}
	}
}
