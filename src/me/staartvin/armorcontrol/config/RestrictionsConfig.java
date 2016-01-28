package me.staartvin.armorcontrol.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.staartvin.armorcontrol.ArmorControl;

public class RestrictionsConfig {

	private ArmorControl plugin;
	private File file;
	private FileConfiguration config;

	public RestrictionsConfig(ArmorControl plugin) {
		this.plugin = plugin;
	}

	// Custom ID Config Methods
	public void reloadConfig() {
		if (this.file == null) {
			this.file = new File(plugin.getDataFolder(), "restrictions.yml");
		}
		this.config = YamlConfiguration.loadConfiguration(this.file);

		// Look for defaults in the jar
		InputStream defConfigStream = plugin.getResource("restrictions.yml");
		if (defConfigStream != null) {
			@SuppressWarnings("deprecation")
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			this.config.setDefaults(defConfig);
		}
	}

	public FileConfiguration getConfig() {
		if (this.config == null) {
			this.reloadConfig();
		}
		return this.config;
	}

	public void loadConfig() {

		config.options().header("This is the restrictions config. Any item you want to restrict must be specified here."
				+ "\nAn item can have restrictions on left clicking air, right clicking air, left clicking blocks, right clicking blocks,"
				+ "\nleft clicking mobs, right clicking mobs, left clicking players, right clicking players, wearing it and shooting it with a bow."
				+ "\nYou don't have to specify levels for all restrictions: if you leave out a certain action, Armor Control assumes you don't want"
				+ "\na restriction for that action and so it defaults to level 0." + "\n" + "\nExample given:" + "\n"
				+ "\nDiamond sword:" 
				+ "\n  item id: 276"
				+ "\n  data value: -1 (sword has no data value, so this optional. Just keep this out)" 
				+ "\n  actions:"
				+ "\n    left click air:"
				+ "\n      requirements:"
				+ "\n        exp level:"
				+ "\n          description: Have at least level 10 in xp."
				+ "\n          value: 10"
				+ "\n        mcmmo level:"
				+ "\n          description: Obtain level 15 in archery."
				+ "\n          value: 15"
				+ "\n  disabled worlds:" 
				+ "\n    - a_disabled_world" 
				+ "\n    - another_disabled_world"
				+ "\n\nThe name does not matter, Armor Control will only look at the item id and data value."
				+ "\nYou don't have to give the data value if it is not needed. For example, cobblestone has no data value, so you don't have to give it."
				+ "\nJust leave the whole line out when it is not needed."
				+ "\nAny action that you do not want to restrict can also be left out."
				+ "\nAll actions that can be used: left click air, right click air, left click block, right click block, left click player, right click player,"
				+ "\nleft click mob, right click mob, wear and shoot bow."
				+ "\n'Disabled worlds' can also be left out if you do not want extra disabled worlds for this item."
				+ "\n\nIf you have any questions, just go to the Spigot thread and ask there!"
				+ "\nHave fun with Armor Control!");

		// Load armor
		// Load leather armor
		this.config.addDefault("Leather Helmet.item id", 298);
		this.config.addDefault("Leather Helmet.actions.wear.requirements.exp level.value", 5);
		this.config.addDefault("Leather Helmet.actions.wear.requirements.exp level.description", "Have at least level 5 in xp.");

		this.config.addDefault("Leather Chestplate.item id", 299);
		this.config.addDefault("Leather Chestplate.actions.wear.requirements.exp level.value", 5);
		this.config.addDefault("Leather Chestplate.actions.wear.requirements.exp level.description", "Have at least level 5 in xp.");

		this.config.addDefault("Leather Leggings.item id", 300);
		this.config.addDefault("Leather Leggings.actions.wear.requirements.exp level.value", 5);
		this.config.addDefault("Leather Leggings.actions.wear.requirements.exp level.description", "Have at least level 5 in xp.");

		this.config.addDefault("Leather Boots.item id", 301);
		this.config.addDefault("Leather Boots.actions.wear.requirements.exp level.value", 5);
		this.config.addDefault("Leather Boots.actions.wear.requirements.exp level.description", "Have at least level 5 in xp.");

		// Load chain armor
		this.config.addDefault("Chain Helmet.item id", 302);
		this.config.addDefault("Chain Helmet.actions.wear.requirements.exp level.value", 10);
		this.config.addDefault("Chain Helmet.actions.wear.requirements.exp level.description", "Have at least level 10 in xp.");

		this.config.addDefault("Chain Chestplate.item id", 303);
		this.config.addDefault("Chain Chestplate.actions.wear.requirements.exp level.value", 10);
		this.config.addDefault("Chain Chestplate.actions.wear.requirements.exp level.description", "Have at least level 10 in xp.");

		this.config.addDefault("Chain Leggings.item id", 304);
		this.config.addDefault("Chain Leggings.actions.wear.requirements.exp level.value", 10);
		this.config.addDefault("Chain Leggings.actions.wear.requirements.exp level.description", "Have at least level 10 in xp.");

		this.config.addDefault("Chain Boots.item id", 305);
		this.config.addDefault("Chain Boots.actions.wear.requirements.exp level.value", 10);
		this.config.addDefault("Chain Boots.actions.wear.requirements.exp level.description", "Have at least level 10 in xp.");

		// Load iron armor
		this.config.addDefault("Iron Helmet.item id", 306);
		this.config.addDefault("Iron Helmet.actions.wear.requirements.exp level.value", 15);
		this.config.addDefault("Iron Helmet.actions.wear.requirements.exp level.description", "Have at least level 15 in xp.");

		this.config.addDefault("Iron Chestplate.item id", 307);
		this.config.addDefault("Iron Chestplate.actions.wear.requirements.exp level.value", 15);
		this.config.addDefault("Iron Chestplate.actions.wear.requirements.exp level.description", "Have at least level 15 in xp.");

		this.config.addDefault("Iron Leggings.item id", 308);
		this.config.addDefault("Iron Leggings.actions.wear.requirements.exp level.value", 15);
		this.config.addDefault("Iron Leggings.actions.wear.requirements.exp level.description", "Have at least level 15 in xp.");

		this.config.addDefault("Iron Boots.item id", 309);
		this.config.addDefault("Iron Boots.actions.wear.requirements.exp level.value", 15);
		this.config.addDefault("Iron Boots.actions.wear.requirements.exp level.description", "Have at least level 15 in xp.");

		// Load golden armor
		this.config.addDefault("Golden Helmet.item id", 314);
		this.config.addDefault("Golden Helmet.actions.wear.requirements.exp level.value", 25);
		this.config.addDefault("Golden Helmet.actions.wear.requirements.exp level.description", "Have at least level 25 in xp.");

		this.config.addDefault("Golden Chestplate.item id", 315);
		this.config.addDefault("Golden Chestplate.actions.wear.requirements.exp level.value", 25);
		this.config.addDefault("Golden Chestplate.actions.wear.requirements.exp level.description", "Have at least level 25 in xp.");

		this.config.addDefault("Golden Leggings.item id", 316);
		this.config.addDefault("Golden Leggings.actions.wear.requirements.exp level.value", 25);
		this.config.addDefault("Golden Leggings.actions.wear.requirements.exp level.description", "Have at least level 25 in xp.");

		this.config.addDefault("Golden Boots.item id", 317);
		this.config.addDefault("Golden Boots.actions.wear.requirements.exp level.value", 25);
		this.config.addDefault("Golden Boots.actions.wear.requirements.exp level.description", "Have at least level 25 in xp.");

		// Load diamond armor
		this.config.addDefault("Diamond Helmet.item id", 310);
		this.config.addDefault("Diamond Helmet.actions.wear.requirements.exp level.value", 30);
		this.config.addDefault("Diamond Helmet.actions.wear.requirements.exp level.description", "Have at least level 30 in xp.");

		this.config.addDefault("Diamond Chestplate.item id", 311);
		this.config.addDefault("Diamond Chestplate.actions.wear.requirements.exp level.value", 30);
		this.config.addDefault("Diamond Chestplate.actions.wear.requirements.exp level.description", "Have at least level 30 in xp.");

		this.config.addDefault("Diamond Leggings.item id", 312);
		this.config.addDefault("Diamond Leggings.actions.wear.requirements.exp level.value", 30);
		this.config.addDefault("Diamond Leggings.actions.wear.requirements.exp level.description", "Have at least level 30 in xp.");

		this.config.addDefault("Diamond Boots.item id", 313);
		this.config.addDefault("Diamond Boots.actions.wear.requirements.exp level.value", 30);
		this.config.addDefault("Diamond Boots.actions.wear.requirements.exp level.description", "Have at least level 30 in xp.");

		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void saveConfig() {
		if (this.config == null || this.file == null) {
			return;
		}
		try {
			getConfig().save(this.file);
		} catch (IOException ex) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.file, ex);
		}
	}
}
