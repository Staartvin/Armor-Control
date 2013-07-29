package Staartvin.ArmorControl.Messages;

import org.bukkit.ChatColor;

import Staartvin.ArmorControl.ArmorControl;

public class MessageHandler {

	private ArmorControl plugin;
	
	public MessageHandler(ArmorControl instance) {
		plugin = instance;
	}
	
	public enum message {NOT_ALLOWED_TO_WEAR}
	
	
	public String getMessage(message msg) {
		if (msg.equals(message.NOT_ALLOWED_TO_WEAR)) {
			return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NOT_ALLOWED_TO_WEAR"));
		} else {
			return null;
		}
	}
}
