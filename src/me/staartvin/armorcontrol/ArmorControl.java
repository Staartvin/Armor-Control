package me.staartvin.armorcontrol;

import org.bukkit.plugin.java.JavaPlugin;

import me.staartvin.armorcontrol.api.API;
import me.staartvin.armorcontrol.commands.Commands;
import me.staartvin.armorcontrol.config.ConfigHandler;
import me.staartvin.armorcontrol.listeners.PlayerInteractListener;
import me.staartvin.armorcontrol.restrictions.RestrictionsManager;
import me.staartvin.armorcontrol.tasks.InventoryArmorTask;

/**
 * @author Staartvin
 *
 */
public class ArmorControl extends JavaPlugin {

	private API api = new API(this);
	private RestrictionsManager resManager = new RestrictionsManager(this);

	private ConfigHandler configHandler;

	public void onEnable() {

		configHandler = new ConfigHandler(this);

		// Load files
		configHandler.loadFiles();

		getCommand("ac").setExecutor(new Commands(this));

		// Schedule new task to check for every player
		new InventoryArmorTask(this).runTaskTimer(this, 100, 20 * 2);

		// Load all restrictions
		resManager.loadRestrictions();
		
		registerListeners();

		this.getLogger().info(getDescription().getName() + " has been enabled!");

	}
	
	private void registerListeners() {
		this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
	}

	public void onDisable() {

		// Cancel all running tasks
		getServer().getScheduler().cancelTasks(this);

		// Reload config and then save
		this.reloadConfig();
		this.saveConfig();

		// Reload restrictions.yml
		this.getConfigHandler().getFile().reloadConfig();
		this.getConfigHandler().getFile().saveConfig();

		this.getLogger().info(getDescription().getName() + " has been disabled!");
	}

	public void debugMessage(String message) {
		if (this.getConfigHandler().verboseLoggingEnabled()) {
			this.getLogger().info(message);
		}
	}

	/**
	 * Get an instance of Armor Control. After an instance has been called, you
	 * can call all methods.
	 * 
	 * @return API class which has all public methods.
	 */
	public API getAPI() {
		return api;
	}

	public ConfigHandler getConfigHandler() {
		return configHandler;
	}

	public void setConfigHandler(ConfigHandler configHandler) {
		this.configHandler = configHandler;
	}

	public RestrictionsManager getResManager() {
		return resManager;
	}

	public void setResManager(RestrictionsManager resManager) {
		this.resManager = resManager;
	}
}
