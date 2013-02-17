package Staartvin.ArmorControl.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import Staartvin.ArmorControl.ArmorControl;

public class EntityDamageListener implements Listener {

	ArmorControl plugin;
	
	public EntityDamageListener (ArmorControl plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onWeaponUse(EntityDamageByEntityEvent event) {
		if (!plugin.getConfig().getBoolean("UseWeaponControl")) return;
		// Damager is not a player for some reason
		if (!(event.getDamager() instanceof Player)) return;
	    Player player = (Player) event.getDamager();
	    // Player has got the correct exempt permission
	    if (player.hasPermission("weaponcontrol.exempt")) return;
	    int IDinHand = player.getItemInHand().getTypeId();
	    System.out.print("Item ID: " + IDinHand);
	    // Nothing in hand
	    if (IDinHand == 0) return;
	    if (IDinHand == 267 || IDinHand == 268 || IDinHand == 272 || IDinHand == 276 || IDinHand == 283 || IDinHand == 261) {
		    for (int ID : plugin.getLevels().getWeaponIDs()) {
		    	if (IDinHand == ID) {
		    		if (!plugin.getMethods().checkLevel(player, plugin.getMethods().checkWeaponType(ID), "weapon")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.RED + "You cannot use this weapon! You must be at least level: " + plugin.getMethods().findLevel(plugin.getMethods().checkWeaponType(ID), "weapon"));
		    		}
		    	}
		    }
	    } else {
			// Wood tools
		    for (int ID : plugin.getLevels().getWoodToolsIDs()) {
		    	if (IDinHand == ID) {
		    		if (!plugin.getMethods().checkLevel(player, "wood", "tool")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.RED + "You cannot use this tool! You must be at least level: " + plugin.getMethods().findLevel("wood", "tool"));
		    		}
		    	}
		    }
		    
		 // Stone tools
		    for (int ID : plugin.getLevels().getStoneToolsIDs()) {
		    	if (IDinHand == ID) {
		    		if (!plugin.getMethods().checkLevel(player, "stone", "tool")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.RED + "You cannot use this tool! You must be at least level: " + plugin.getMethods().findLevel("stone", "tool"));
		    		}
		    	}
		    }
		    
		 // Iron tools
		    for (int ID :plugin.getLevels().getIronToolsIDs()) {
		    	if (IDinHand == ID) {
		    		if (!plugin.getMethods().checkLevel(player, "iron", "tool")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.RED + "You cannot use this tool! You must be at least level: " + plugin.getMethods().findLevel("iron", "tool"));
		    		}
		    	}
		    }
		    
		 // Gold tools
		    for (int ID : plugin.getLevels().getGoldToolsIDs()) {
		    	if (IDinHand == ID) {
		    		if (!plugin.getMethods().checkLevel(player, "gold", "tool")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.RED + "You cannot use this tool! You must be at least level: " + plugin.getMethods().findLevel("gold", "tool"));
		    		}
		    	}
		    }
		    
		 // Diamond tools
		    for (int ID : plugin.getLevels().getDiamondToolsIDs()) {
		    	if (IDinHand == ID) {
		    		if (!plugin.getMethods().checkLevel(player, "diamond", "tool")) {
		    			event.setCancelled(true);
		    			player.sendMessage(ChatColor.RED + "You cannot use this tool! You must be at least level: " + plugin.getMethods().findLevel("diamond", "tool"));
		    		}
		    	}
		    }
	    }   
	}
}