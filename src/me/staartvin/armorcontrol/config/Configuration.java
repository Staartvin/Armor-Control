package me.staartvin.armorcontrol.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.staartvin.armorcontrol.ArmorControl;

public class Configuration {

	private ArmorControl plugin;
	private File file;
	private FileConfiguration config;

	public Configuration(ArmorControl plugin) {
		this.plugin = plugin;
	}

	// Custom ID Config Methods
	public void reloadConfig() {
		if (this.file == null) {
			this.file = new File(plugin.getDataFolder(),
					"restrictions.yml");
		}
		this.config = YamlConfiguration
				.loadConfiguration(this.file);

		// Look for defaults in the jar
		InputStream defConfigStream = plugin.getResource("restrictions.yml");
		if (defConfigStream != null) {
			@SuppressWarnings("deprecation")
			YamlConfiguration defConfig = YamlConfiguration
					.loadConfiguration(defConfigStream);
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
		
		// Load chain armor
		this.config.addDefault("Chain Helmet.item id", 302);
		this.config.addDefault("Chain Helmet.actions.left click air", 0);
		this.config.addDefault("Chain Helmet.actions.right click air", 0);
		this.config.addDefault("Chain Helmet.actions.left click block", 0);
		this.config.addDefault("Chain Helmet.actions.right click block", 0);
		this.config.addDefault("Chain Helmet.actions.left click mob", 0);
		this.config.addDefault("Chain Helmet.actions.right click mob", 0);
		this.config.addDefault("Chain Helmet.actions.wear", 2);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void saveConfig() {
		if (this.config == null
				|| this.file == null) {
			return;
		}
		try {
			getConfig().save(this.file);
		} catch (IOException ex) {
			plugin.getLogger().log(Level.SEVERE,
					"Could not save config to " + this.file,
					ex);
		}
	}
}
