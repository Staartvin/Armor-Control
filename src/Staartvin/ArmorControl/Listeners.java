package Staartvin.ArmorControl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class Listeners implements Listener {

	ArmorControl plugin;
	
	public Listeners (ArmorControl plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		// Player is not online for some reason
		if (plugin.getServer().getPlayer(event.getPlayer().getName()) == null) return;
	    Player player = plugin.getServer().getPlayer(event.getPlayer().getName());
	    // Player hasn't got the correct permission
	    if (player.hasPermission("armorcontrol.exempt")) return;
	    
	    plugin.inv = plugin.getServer().getPlayer(event.getPlayer().getName()).getInventory();
	    plugin.methods.checkInventory(player); 
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		// Player is not online for some reason
		if (plugin.getServer().getPlayer(event.getPlayer().getName()) == null) return;
	    Player player = plugin.getServer().getPlayer(event.getPlayer().getName());
	 // Player hasn't got the correct permission
	    if (player.hasPermission("armorcontrol.exempt")) return;
	    
	    plugin.inv = plugin.getServer().getPlayer(event.getPlayer().getName()).getInventory();
	    plugin.methods.checkInventory(player); 
	}
}
