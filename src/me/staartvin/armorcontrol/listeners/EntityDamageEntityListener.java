package me.staartvin.armorcontrol.listeners;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.message;
import me.staartvin.armorcontrol.requirements.Requirement;
import me.staartvin.armorcontrol.restrictions.Restriction;
import me.staartvin.armorcontrol.restrictions.RestrictionsManager.actionType;
import net.md_5.bungee.api.ChatColor;

public class EntityDamageEntityListener implements Listener {

	ArmorControl plugin;

	public EntityDamageEntityListener(ArmorControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteract(EntityDamageByEntityEvent event) {

		Entity attacker = event.getDamager();

		// Attacker is not a player.
		if (!attacker.getType().equals(EntityType.PLAYER))
			return;
		
		String worldName = attacker.getLocation().getWorld().getName();

		// We do not have to check on this world.
		if (plugin.getAPI().isDisabledWorld(worldName))
			return;

		Player player = (Player) attacker;

		// Ignore creative?
		if (plugin.getConfigHandler().shouldIgnoreCreative()) {
			if (player.getGameMode().equals(GameMode.CREATIVE))
				return;
		}

		actionType action = actionType.LEFT_CLICK_MOB;

		if (event.getEntity().getType().equals(EntityType.PLAYER)) {
			// Action is left clicked player
			action = actionType.LEFT_CLICK_PLAYER;
		}

		ItemStack item = player.getItemInHand();

		// No item in hand, so nothing to check
		if (item == null)
			return;

		String blockAction = action.toString();

		// Check if restrictions are disabled on this world.
		if (plugin.getAPI().isDisabledWorld(item, worldName)) {
			return;
		}

		Restriction r = plugin.getResManager().getRestriction(item);

		// No restriction for this item found.
		if (r == null)
			return;

		List<Requirement> failed = r.getFailedRequirements(player, action);

		// All requirements are met, allow to use.
		if (failed.isEmpty())
			return;

		// Cannot use this item
		event.setCancelled(true);

		plugin.getMessageHandler().sendMessage(player,
				plugin.getConfigHandler().getMessage(message.NOT_ALLOWED_TO_USE, blockAction));

		// Show what the player still has to do to use this item.
		for (Requirement failedReq : failed) {
			player.sendMessage(ChatColor.RED + "- " + failedReq.getDescription());
		}
	}
}
