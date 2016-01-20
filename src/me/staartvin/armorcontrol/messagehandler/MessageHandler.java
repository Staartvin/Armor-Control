package me.staartvin.armorcontrol.messagehandler;

import java.util.HashMap;

import org.bukkit.entity.Player;

import me.staartvin.armorcontrol.ArmorControl;

public class MessageHandler {

	private HashMap<String, Boolean> readyToSend = new HashMap<String, Boolean>();
	
	private ArmorControl plugin;
	
	public MessageHandler(ArmorControl instance) {
		plugin = instance;
	}
	
	public void sendMessage(final Player player, String message) {
		boolean ready = false;
		
		if (!readyToSend.containsKey(player.getName())) {
			// No record, so send it immediately.
			ready = true;
		} else {
			ready = readyToSend.get(player.getName());
		}
		
		// Only send message if ready.
		if (ready) {
			player.sendMessage(message);
		}
		
		// We send a message, now wait.
		readyToSend.put(player.getName(), false);
		
		// Set ready to true after 1 seconds.
		plugin.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
			public void run() {
				readyToSend.put(player.getName(), true);
			}
		}, 20 * 1);
	}
}
