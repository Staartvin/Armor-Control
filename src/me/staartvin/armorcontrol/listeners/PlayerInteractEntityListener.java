package me.staartvin.armorcontrol.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.message;
import me.staartvin.armorcontrol.restrictions.RestrictionsManager.actionType;

public class PlayerInteractEntityListener implements Listener {

	ArmorControl plugin;

	public PlayerInteractEntityListener(ArmorControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {

		Player player = event.getPlayer();
		
		String worldName = player.getLocation().getWorld().getName();

		// We do not have to check on this world.
		if (plugin.getAPI().isDisabledWorld(worldName))
			return;

		// Ignore creative?
		if (plugin.getConfigHandler().shouldIgnoreCreative()) {
			if (player.getGameMode().equals(GameMode.CREATIVE))
				return;
		}

		actionType action = actionType.RIGHT_CLICK_MOB;

		if (event.getRightClicked().getType().equals(EntityType.PLAYER)) {
			// Action is right clicked player
			action = actionType.RIGHT_CLICK_PLAYER;
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
