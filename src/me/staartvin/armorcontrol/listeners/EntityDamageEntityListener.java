package me.staartvin.armorcontrol.listeners;

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
import me.staartvin.armorcontrol.restrictions.RestrictionsManager.actionType;

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

		int requiredLevel = 0;

		requiredLevel = plugin.getResManager().getRequiredLevel(item, action);

		// Check if restrictions are disabled on this world.
		if (plugin.getAPI().isDisabledWorld(item, worldName)) {
			return;
		}

		// Required level is 0.
		if (requiredLevel <= 0)
			return;

		// Player does not have the sufficient level
		if (requiredLevel > player.getLevel()) {
			plugin.getMessageHandler().sendMessage(player,
					plugin.getConfigHandler().getMessage(message.NOT_ALLOWED_TO_USE, blockAction, requiredLevel + ""));

			event.setCancelled(true);
		}
	}
}
