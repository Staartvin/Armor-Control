package me.staartvin.armorcontrol.commands;

import me.staartvin.armorcontrol.ArmorControl;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

	ArmorControl plugin;

	public Commands(ArmorControl instance) {
		plugin = instance;
	}

	public boolean hasPermission(String permission, CommandSender sender) {
		if (!sender.hasPermission(permission)) {
			sender.sendMessage(ChatColor.RED + "You need to have ("
					+ permission + ") to do this!");
			return false;
		}
		return true;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if (args.length == 0) {
			sender.sendMessage(ChatColor.BLUE
					+ "-----------------------------------------------------");
			sender.sendMessage(ChatColor.GOLD + "Developed by: "
					+ ChatColor.GRAY + "Staartvin");
			sender.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.GRAY
					+ plugin.getDescription().getVersion());
			sender.sendMessage(ChatColor.YELLOW
					+ "Type /ac help for a list of commands.");
			return true;
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				if (!hasPermission("armorcontrol.reload", sender))
					return true;

				plugin.getServer().getPluginManager().disablePlugin(plugin);
				plugin.getServer().getPluginManager().enablePlugin(plugin);

				sender.sendMessage(ChatColor.GREEN
						+ "Armor Control has been successfully reloaded!");
				return true;
			} else if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage(ChatColor.BLUE + "---------------["
						+ ChatColor.GOLD + "Armor Control" + ChatColor.BLUE
						+ "]--------------------");
				sender.sendMessage(ChatColor.GOLD + "/armorcontrol"
						+ ChatColor.BLUE
						+ " --- Shows info about Armor Control");
				sender.sendMessage(ChatColor.GOLD + "/ac help" + ChatColor.BLUE
						+ " --- Shows a list of commands");
				sender.sendMessage(ChatColor.GOLD + "/ac reload"
						+ ChatColor.BLUE + " --- Reloads Armor Control");
				sender.sendMessage(ChatColor.GOLD + "/ac add <name> <data value> <level>"
						+ ChatColor.BLUE + " --- Add a custom ID");
				return true;
			}
		} else if (args.length == 4) {
			// /ac add <name> <data value> <level>
			
			if (args[0].equalsIgnoreCase("add")) {
				if (!hasPermission("armorcontrol.add", sender))
					return true;
				
				int dataValue = 0;
				int level = 0;
				String name = args[1];
				
				try {
					dataValue = Integer.parseInt(args[2]);
				} catch (Exception e) {
					sender.sendMessage(ChatColor.RED + args[2] + " is not a number!");
					return true;
				}
				
				try {
					level = Integer.parseInt(args[3]);
				} catch (Exception e) {
					sender.sendMessage(ChatColor.RED + args[3] + " is not a number!");
					return true;
				}
				
				if (!plugin.getConfiguration().addCustomID(name, dataValue, level)) {
					sender.sendMessage(ChatColor.RED + "Custom ID with name '" + name + "' does already exist!");
					return true;
				}
				sender.sendMessage(ChatColor.GREEN + "Custom ID '" + name + "' with data value " + dataValue + " and level " + level + " added!");
				return true;
			}
		}
			

		sender.sendMessage(ChatColor.BLUE
				+ "-----------------------------------------------------");
		sender.sendMessage(ChatColor.RED + "Command not recognised!");
		sender.sendMessage(ChatColor.GOLD
				+ "Type /ac help to get a list of commands.");
		return true;
	}
}
