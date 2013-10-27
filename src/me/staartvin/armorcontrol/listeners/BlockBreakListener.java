package me.staartvin.armorcontrol.listeners;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.messages.MessageHandler.message;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

	ArmorControl plugin;
	
	public BlockBreakListener (ArmorControl plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onToolUse(BlockBreakEvent event) {		
		if (!plugin.getConfig().getBoolean("UseToolControl")) return;
		
		// Player is not online for some reason
		if (event.getPlayer() == null) return;
	    Player player = event.getPlayer();
	    
	    // Is this world disabled
	    if (plugin.getWorldHandler().isDisabled(player.getWorld().getName())) return;
	    
	    // Player has got the correct exempt permission
	    if (player.hasPermission("toolcontrol.exempt")) return;
	    int IDinHand = player.getItemInHand().getTypeId();
	    
	    // Check for custom IDs
	    if (plugin.getMethods().isNotAllowedCustomID(IDinHand, player)) {
    		event.setCancelled(true);
    		return;
	    }
	    if (IDinHand == 0) return;
	    // Tool in hand is a weapon
	    if (IDinHand == 267 || IDinHand == 268 || IDinHand == 272 || IDinHand == 276 || IDinHand == 283 || IDinHand == 261) return;
	    
	    
		// Wood tools
	    for (int ID : plugin.getLevels().getWoodToolsIDs()) {
	    	if (IDinHand == ID) {
	    		if (!plugin.getMethods().checkLevel(player, "wood", "tool")) {
	    			event.setCancelled(true);
	    			player.sendMessage(plugin.getMessageHandler().getMessage(message.NOT_ALLOWED_TO_USE_TOOL).replace("%level%", plugin.getMethods().findLevel("wood", "tool") + ""));
	    		}
	    	}
	    }
	    
	 // Stone tools
	    for (int ID : plugin.getLevels().getStoneToolsIDs()) {
	    	if (IDinHand == ID) {
	    		if (!plugin.getMethods().checkLevel(player, "stone", "tool")) {
	    			event.setCancelled(true);
	    			player.sendMessage(plugin.getMessageHandler().getMessage(message.NOT_ALLOWED_TO_USE_TOOL).replace("%level%", plugin.getMethods().findLevel("stone", "tool") + ""));
	    		}
	    	}
	    }
	    
	 // Iron tools
	    for (int ID : plugin.getLevels().getIronToolsIDs()) {
	    	if (IDinHand == ID) {
	    		if (!plugin.getMethods().checkLevel(player, "iron", "tool")) {
	    			event.setCancelled(true);
	    			player.sendMessage(plugin.getMessageHandler().getMessage(message.NOT_ALLOWED_TO_USE_TOOL).replace("%level%", plugin.getMethods().findLevel("iron", "tool") + ""));
	    		}
	    	}
	    }
	    
	 // Gold tools
	    for (int ID : plugin.getLevels().getGoldToolsIDs()) {
	    	if (IDinHand == ID) {
	    		if (!plugin.getMethods().checkLevel(player, "gold", "tool")) {
	    			event.setCancelled(true);
	    			player.sendMessage(plugin.getMessageHandler().getMessage(message.NOT_ALLOWED_TO_USE_TOOL).replace("%level%", plugin.getMethods().findLevel("gold", "tool") + ""));
	    		}
	    	}
	    }
	    
	 // Diamond tools
	    for (int ID : plugin.getLevels().getDiamondToolsIDs()) {
	    	if (IDinHand == ID) {
	    		if (!plugin.getMethods().checkLevel(player, "diamond", "tool")) {
	    			event.setCancelled(true);
	    			player.sendMessage(plugin.getMessageHandler().getMessage(message.NOT_ALLOWED_TO_USE_TOOL).replace("%level%", plugin.getMethods().findLevel("diamond", "tool") + ""));
	    		}
	    	}
	    }
	   }
}