package Staartvin.ArmorControl;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorControl extends JavaPlugin implements Listener {

	int[] helmetIDs = {298, 302, 306, 310, 314};
	int[] chestplateIDs = {299, 303, 307, 311, 315};
	int[] leggingIDs = {300, 304, 308, 312, 316};
	int[] bootIDs = {301, 305, 309, 313, 317};
	ItemStack armorPart;
	ItemStack air = new ItemStack(Material.AIR, 1);
	PlayerInventory inv;
	
	int leatherLevel = 5;
	int chainLevel = 11;
	int goldLevel = 17;
	int ironLevel = 22;
	int diamondLevel = 30;
	
	Listeners listener = new Listeners(this);
	Methods methods = new Methods(this);
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(listener, this);
		loadConfiguration();
		methods.checkLimits();
		System.out.println("[" + getDescription().getName()
				+ "] has been enabled!");
	}

	public void onDisable() {
		reloadConfig();
		saveConfig();
		System.out.println("[" + getDescription().getName()
				+ "] has been disabled!");
	}

	public void loadConfiguration() {
		getConfig().options().header(
				"ArmorControl v" + getDescription().getVersion() + " Config");
		
		getConfig().addDefault("verboseLogging", true);
		getConfig().addDefault("leatherLevel", 5);
		getConfig().addDefault("chainLevel", 11);
		getConfig().addDefault("goldLevel", 17);
		getConfig().addDefault("ironLevel", 22);
		getConfig().addDefault("diamondLevel", 30);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
