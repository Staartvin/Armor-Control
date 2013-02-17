package Staartvin.ArmorControl.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import Staartvin.ArmorControl.ArmorControl;

public class EntityBowListener implements Listener {

	ArmorControl plugin;
	
	public EntityBowListener (ArmorControl plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBowUse(EntityShootBowEvent event) {
		if (!plugin.getConfig().getBoolean("UseWeaponControl")) return;
		// Entity is not a player
		if (!(event.getEntity() instanceof Player)) return;
	    Player player = (Player) event.getEntity();
	    // Player hasn't got the correct permission
	    if (player.hasPermission("weaponcontrol.exempt")) return;
	    
	    if (player.getLevel() < plugin.getLevels().getBowLevel()) {
	     player.sendMessage(ChatColor.RED + "You cannot use a bow! You must be at least level: " + plugin.getLevels().getBowLevel());
	     event.setCancelled(true);
	    }
	}
}
