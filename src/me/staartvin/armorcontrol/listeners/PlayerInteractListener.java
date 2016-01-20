package me.staartvin.armorcontrol.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.actionType;
import me.staartvin.armorcontrol.config.ConfigHandler.message;

public class PlayerInteractListener implements Listener {

	ArmorControl plugin;

	public PlayerInteractListener(ArmorControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		// We do not have to check on this world.
		if (plugin.getAPI().isDisabledWorld(player.getLocation().getWorld().getName()))
			return;

		ItemStack item = event.getItem();

		// No item in hand, so nothing to check
		if (item == null)
			return;

		Action action = event.getAction();

		String blockAction = action.toString();

		int requiredLevel = 0;
		System.out.println("------------------------");

		for (actionType type : actionType.values()) {

			if (blockAction.equalsIgnoreCase(type.toString())) {
				requiredLevel = plugin.getResManager().getRequiredLevel(item, type);
				break;
			}
		}

		System.out.println("Required level: " + requiredLevel);

		// Required level is 0.
		if (requiredLevel <= 0)
			return;

		// Player does not have the sufficient level
		if (requiredLevel > player.getLevel()) {
			plugin.getMessageHandler().sendMessage(player,
					plugin.getConfigHandler().getMessage(message.NOT_ALLOWED_TO_USE, action.toString(), requiredLevel + ""));

			event.setCancelled(true);

			System.out.println("Event is cancelled.");
		}
	}
}
