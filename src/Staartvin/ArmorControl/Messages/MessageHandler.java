package Staartvin.ArmorControl.Messages;

import org.bukkit.ChatColor;

import Staartvin.ArmorControl.ArmorControl;

public class MessageHandler {

	private ArmorControl plugin;
	
	public MessageHandler(ArmorControl instance) {
		plugin = instance;
	}
	
	public enum message {NOT_ALLOWED_TO_WEAR_ARMOR, NOT_ALLOWED_TO_USE_TOOL, NOT_ALLOWED_TO_USE_WEAPON, NOT_ALLOWED_TO_SHOOT_BOW}
	
	
	public String getMessage(message msg) {
		if (msg.equals(message.NOT_ALLOWED_TO_WEAR_ARMOR)) {
			return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NOT_ALLOWED_TO_WEAR_ARMOR"));
		} else if (msg.equals(message.NOT_ALLOWED_TO_USE_TOOL)) {
			return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NOT_ALLOWED_TO_USE_TOOL"));
		} else if (msg.equals(message.NOT_ALLOWED_TO_USE_WEAPON)) {
			return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NOT_ALLOWED_TO_USE_WEAPON"));
		} else if (msg.equals(message.NOT_ALLOWED_TO_SHOOT_BOW)) {
			return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.NOT_ALLOWED_TO_SHOOT_BOW"));
		}
		
		else {
			return null;
		}
	}
}
