package me.staartvin.armorcontrol;

import java.io.File;

import me.staartvin.armorcontrol.commands.Commands;
import me.staartvin.armorcontrol.listeners.BlockBreakListener;
import me.staartvin.armorcontrol.listeners.EntityBowListener;
import me.staartvin.armorcontrol.listeners.EntityDamageListener;
import me.staartvin.armorcontrol.listeners.InventoryListener;
import me.staartvin.armorcontrol.messages.MessageHandler;
import me.staartvin.armorcontrol.tasks.InventoryArmorTask;
import me.staartvin.armorcontrol.worldhandler.WorldHandler;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Staartvin
 *
 */
public class ArmorControl extends JavaPlugin {

	
	private Methods methods = new Methods(this);
	private API api = new API(this);
	private Configuration config = new Configuration(this);
	private CustomIDs customIDsClass = new CustomIDs(this);
	private Levels levels = new Levels(this);
	private WorldHandler worldHandler = new WorldHandler(this);
	private MessageHandler messageHandler = new MessageHandler(this);
	
	protected FileConfiguration customIDsConfig;
	protected File customIDsConfigFile;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
		getServer().getPluginManager().registerEvents(new EntityBowListener(this), this);
		getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
		getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
		
		config.reloadCustomIDsConfig();
		methods.loadConfiguration();
		methods.upgradeConfig("1.2-to-1.3");
		methods.readLimits();
		customIDsClass.loadCustomIDs();
		
		getCommand("ac").setExecutor(new Commands(this));
		
		// Schedule new task to check for every player
		new InventoryArmorTask(this).runTaskTimer(this, 100, 600);
		
		System.out.println("[" + getDescription().getName()
				+ "] has been enabled!");
	}

	public void onDisable() {
		
		// Cancel all running tasks
		getServer().getScheduler().cancelTasks(this);
		
		reloadConfig();
		saveConfig();
		config.reloadCustomIDsConfig();
		config.saveCustomIDsConfig();
		System.out.println("[" + getDescription().getName()
				+ "] has been disabled!");
	}
	
	/**
	 * Get an instance of Armor Control.
	 * After an instance has been called, you can call all methods.
	 * @return API class which has all public methods.
	 */
	public API getInstance() {
		return api;
	}
	
	public Methods getMethods() {
		return methods;
	}
	
	public Configuration getConfiguration() {
		return config;
	}
	
	public CustomIDs getCustomIDClass() {
		return customIDsClass;
	}
	
	public Levels getLevels() {
		return levels;
	}

	public WorldHandler getWorldHandler() {
		return worldHandler;
	}
	
	public MessageHandler getMessageHandler() {
		return messageHandler;
	}
}
