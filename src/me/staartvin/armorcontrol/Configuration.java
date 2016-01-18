package me.staartvin.armorcontrol;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configuration {

	ArmorControl plugin;

	public Configuration(ArmorControl plugin) {
		this.plugin = plugin;
	}

	// Custom ID Config Methods
	protected void reloadCustomIDsConfig() {
		if (plugin.customIDsConfigFile == null) {
			plugin.customIDsConfigFile = new File(plugin.getDataFolder(),
					"customIDs.yml");
		}
		plugin.customIDsConfig = YamlConfiguration
				.loadConfiguration(plugin.customIDsConfigFile);

		// Look for defaults in the jar
		InputStream defConfigStream = plugin.getResource("customIDs.yml");
		if (defConfigStream != null) {
			@SuppressWarnings("deprecation")
			YamlConfiguration defConfig = YamlConfiguration
					.loadConfiguration(defConfigStream);
			plugin.customIDsConfig.setDefaults(defConfig);
		}
	}

	protected FileConfiguration getCustomIDsConfig() {
		if (plugin.customIDsConfig == null) {
			this.reloadCustomIDsConfig();
		}
		return plugin.customIDsConfig;
	}

	protected void saveCustomIDsConfig() {
		if (plugin.customIDsConfig == null
				|| plugin.customIDsConfigFile == null) {
			return;
		}
		try {
			getCustomIDsConfig().save(plugin.customIDsConfigFile);
		} catch (IOException ex) {
			plugin.getLogger().log(Level.SEVERE,
					"Could not save config to " + plugin.customIDsConfigFile,
					ex);
		}
	}

	public boolean addCustomID(String name, Integer dataValue, Integer level) {
		if (plugin.customIDsConfig.getInt("Custom IDs." + name + ".Data value") != 0) {
			return false;
		}

		plugin.customIDsConfig.set("Custom IDs." + name + ".Data value",
				dataValue);
		plugin.customIDsConfig.set("Custom IDs." + name + ".Level", level);

		saveCustomIDsConfig();
		return true;
	}
}
