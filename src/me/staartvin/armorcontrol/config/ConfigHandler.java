package me.staartvin.armorcontrol.config;

import java.util.ArrayList;
import java.util.List;

import me.staartvin.armorcontrol.ArmorControl;
import net.md_5.bungee.api.ChatColor;

public class ConfigHandler {

	private ArmorControl plugin;
	private Configuration restrictionsConfig;

	public enum message {
		NOT_ALLOWED_TO_WEAR_ARMOR, NOT_ALLOWED_TO_USE, NOT_ALLOWED_TO_SHOOT_BOW
	}

	public static enum actionType {
		LEFT_CLICK_AIR, RIGHT_CLICK_AIR, LEFT_CLICK_BLOCK, RIGHT_CLICK_BLOCK, LEFT_CLICK_MOB, RIGHT_CLICK_MOB, WEAR, LEFT_CLICK_PLAYER, RIGHT_CLICK_PLAYER
	};

	public ConfigHandler(ArmorControl instance) {
		plugin = instance;
		restrictionsConfig = new Configuration(instance);
		
		this.restrictionsConfig.reloadConfig();
		instance.reloadConfig();
	}

	public void loadFiles() {
		
		// Load restrictions config.
		this.restrictionsConfig.loadConfig();

		plugin.getConfig().options()
				.header("ArmorControl v" + plugin.getDescription()
						.getVersion() + " Config" + "\nDo not touch the 'upgrade' part. It will mess up the config."
				+ "\nLatest Spigot version tested on: 1.8.8"
				+ "\n\nThanks for using Armor Control! Questions? http://dev.bukkit.org/server-mods/armor-control/");

		plugin.getConfig().addDefault("verboseLogging", true);

		// Disabled worlds
		plugin.getConfig().addDefault("Disabled Worlds",
				new String[] { "DisabledWorld", "DisabledWorld_nether", "DisabledWorld_the_end" });

		// Messages
		plugin.getConfig().addDefault("Messages." + message.NOT_ALLOWED_TO_WEAR_ARMOR.toString(),
				"&cYou cannot wear this item! You must be at least level &6{0}.");
		plugin.getConfig().addDefault("Messages." + message.NOT_ALLOWED_TO_USE.toString(),
				"&cYou cannot use this item this way! ({0}) You must be at least level &6{1}.");
		plugin.getConfig().addDefault("Messages." + message.NOT_ALLOWED_TO_SHOOT_BOW.toString(),
				"&cYou cannot use a bow! You must be at least level &6{0}");

		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}

	public Configuration getFile() {
		return this.restrictionsConfig;
	}

	public int getActionLevel(int itemID, int dataValue, actionType type) {
		// Get the level of an item for a particular action

		// Invalid action type
		if (type == null)
			return 0;

		String itemName = this.findItemName(itemID, dataValue);

		// No restriction for this item
		if (itemName == null)
			return 0;

		String typeString = type.toString().toLowerCase().replace("_", " ");

		return this.restrictionsConfig.getConfig().getInt(itemName + ".actions." + typeString, 0);
	}

	public String findItemName(int itemID, int dataValue) {
		// Used to find the name in the config so we can grab data of off it.

		for (String name : this.restrictionsConfig.getConfig().getKeys(false)) {
			if (this.getItemID(name) == itemID && this.getDataValue(name) == dataValue) {
				return name;
			}
		}

		return null;
	}

	public int getItemID(String name) {
		if (name == null)
			return -1;
		
		return this.restrictionsConfig.getConfig().getInt(name + ".item id", -1);
	}

	public int getDataValue(String name) {
		if (name == null)
			return -1;

		return this.restrictionsConfig.getConfig().getInt(name + ".data value", -1);
	}

	public List<String> getRestrictedItems() {
		List<String> items = new ArrayList<String>();

		for (String name : this.restrictionsConfig.getConfig().getKeys(false)) {
			if (name != null) {
				items.add(name);
			}
		}

		return items;
	}

	public String getMessage(message mess, String... strings) {
		
		String message = plugin.getConfig().getString("Messages." + mess.toString());
		
		for (int i=0;i<strings.length;i++) {
			message = message.replace("{" + i + "}", strings[i]);
		}
		
		return ChatColor.translateAlternateColorCodes('&', message);
	}
	
	public List<String> getDisabledWorlds() {
		return plugin.getConfig().getStringList("Disabled Worlds");
	}
	
	public boolean verboseLoggingEnabled() {
		return plugin.getConfig().getBoolean("verboseLogging");
	}

}
