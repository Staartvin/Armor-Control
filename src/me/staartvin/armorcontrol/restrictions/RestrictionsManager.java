package me.staartvin.armorcontrol.restrictions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.actionType;
import me.staartvin.armorcontrol.config.ConfigHandler.message;

public class RestrictionsManager {

	private ArmorControl plugin;

	private List<Restriction> restrictions = new ArrayList<Restriction>();

	public RestrictionsManager(ArmorControl instance) {
		plugin = instance;
	}

	public void loadRestrictions() {
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
			
			// Set item id
			r.setItemID(itemID);
			
			// For every action, store a level.
			for (actionType action : actionType.values()) {
				r.setActionLevel(action, plugin.getConfigHandler().getActionLevel(itemID, dataValue, action));
			}

			if (dataValue >= 0) {
				r.setDataValue(dataValue);
			}
			
			// Save this restriction
			restrictions.add(r);
			
			count++;
			
			System.out.println("Registered " + itemID + ";" + dataValue + ", " + r.toString());
		}
		
		plugin.debugMessage("Loaded " + count + " restrictions!");
	}

	public int getRequiredLevel(int itemID, int dataValue, actionType type) {
		
		for (Restriction entry: restrictions) {
			if (entry.getItemID() != itemID) continue;
			
			System.out.println("Res: " + entry);
			System.out.println("ItemID: " + itemID);
			
			System.out.println("Data value: " + dataValue);
			System.out.println("Data value of check: " + entry.getDataValue());
			
			if (!entry.hasEqualDataValue(dataValue)) 
				continue;
			
			System.out.println("EQUAL!");

			return entry.getActionLevel(type);
		}
		
		return 0;
	}
	
	@SuppressWarnings("deprecation")
	public int getRequiredLevel(ItemStack item, actionType type) {
		
		int dataValue = 0;
		System.out.println("GET DATA: " + item.getData().getData());
		
		if (shouldIgnoreDataValue(item) || item.getDurability() == 0) {
			dataValue = -1;
		}
		
		return this.getRequiredLevel(item.getTypeId(), dataValue, type);
	}

	@SuppressWarnings("deprecation")
	public void checkArmor(Player player) {
		// Check if player is wearing restricted items
		PlayerInventory inv = player.getInventory();

		int itemID = 0, dataValue = 0;
		ItemStack item;

		actionType type = actionType.WEAR;

		// Slot 36 to 39 are armor slots.
		for (int i = 36; i < 40; i++) {
			item = inv.getItem(i);
			
			if (item == null)
				continue;

			itemID = item.getTypeId();
			dataValue = item.getDurability();
			
			if (dataValue == 0) {
				dataValue = -1;
			}

			int requiredLevel = plugin.getResManager().getRequiredLevel(itemID, dataValue, type);

			// Cannot wear this item
			if (player.getLevel() < requiredLevel) {
				// Remove it from slot and give it back.
				player.getInventory().setItem(i, null);

				this.giveItem(player, item);

				plugin.getMessageHandler().sendMessage(player, plugin.getConfigHandler().getMessage(message.NOT_ALLOWED_TO_WEAR_ARMOR, requiredLevel + ""));
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
		// Since tools, weapons, bows and armor use durability we need to ignore that durability value
		// Since these items all have a stack size of 1, we can safely assume that those durability values can be ignored.
		return item.getMaxStackSize() == 1;
	}
}
