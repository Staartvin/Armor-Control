package me.staartvin.armorcontrol.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.actionType;

public class PlayerInteractListener implements Listener {

	ArmorControl plugin;

	public PlayerInteractListener(ArmorControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		// We do not have to check on this world.
		if (plugin.getAPI().isDisabledWorld(event.getPlayer().getLocation().getWorld().getName())) return;
		
		ItemStack item = event.getItem();
		
		// No item in hand, so nothing to check
		if (item == null) return;
		
		Action action = event.getAction();
		
		String blockAction = action.toString();
		
		int requiredLevel = 0;
		System.out.println("------------------------");
		
		for (actionType type: actionType.values()) {
			
			if (blockAction.equalsIgnoreCase(type.toString())) {
				requiredLevel = plugin.getResManager().getRequiredLevel(item, type);
				break;
			}
		}
		
		System.out.println("Required level: " + requiredLevel);
	}
}
