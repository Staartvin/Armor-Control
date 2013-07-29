package Staartvin.ArmorControl.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import Staartvin.ArmorControl.ArmorControl;

public class InventoryListener implements Listener {

	ArmorControl plugin;

	public InventoryListener(ArmorControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if (!plugin.getConfig().getBoolean("UseArmorControl"))
			return;
		// Player is not online for some reason
		if (plugin.getServer().getPlayer(event.getPlayer().getName()) == null)
			return;
		Player player = plugin.getServer().getPlayer(
				event.getPlayer().getName());

		// Is this world disabled
		if (plugin.getWorldHandler().isDisabled(player.getWorld().getName()))
			return;
		// Player hasn't got the correct permission
		if (player.hasPermission("armorcontrol.exempt"))
			return;
		
		plugin.getMethods().checkInventoryforArmor(player);
	}
}
