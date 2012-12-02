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
	
	int[] woodToolsIDs = {269, 270, 271, 290};
	int[] stoneToolsIDs = {273, 274, 275, 291};
	int[] ironToolsIDs = {256, 257, 258, 292};
	int[] goldToolsIDs = {284, 285, 286, 294};
	int[] diamondToolsIDs = {277, 278, 279, 293};
	
	int[] weaponIDs = {267, 268, 272, 276, 283, 261};
	
	ItemStack armorPart;
	ItemStack air = new ItemStack(Material.AIR, 1);
	PlayerInventory inv;
	
	int leatherArmorLevel = 5;
	int chainArmorLevel = 11;
	int goldArmorLevel = 17;
	int ironArmorLevel = 22;
	int diamondArmorLevel = 30;
	
	int woodToolLevel = 5;
	int stoneToolLevel = 11;
	int goldToolLevel = 17;
	int ironToolLevel = 22;
	int diamondToolLevel = 30;
	
	int woodWeaponLevel = 5;
	int stoneWeaponLevel = 11;
	int goldWeaponLevel = 17;
	int ironWeaponLevel = 22;
	int diamondWeaponLevel = 30;
	
	int bowLevel = 15;
	
	Listeners listener = new Listeners(this);
	Methods methods = new Methods(this);
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(listener, this);
		loadConfiguration();
		methods.readLimits();
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
		
		getConfig().addDefault("leatherArmorLevel", 5);
		getConfig().addDefault("chainArmorLevel", 11);
		getConfig().addDefault("goldArmorLevel", 17);
		getConfig().addDefault("ironArmorLevel", 22);
		getConfig().addDefault("diamondArmorLevel", 30);
		
		getConfig().addDefault("woodToolLevel", 5);
		getConfig().addDefault("stoneToolLevel", 11);
		getConfig().addDefault("goldToolLevel", 17);
		getConfig().addDefault("ironToolLevel", 22);
		getConfig().addDefault("diamondToolLevel", 30);
		
		getConfig().addDefault("woodWeaponLevel", 5);
		getConfig().addDefault("stoneWeaponLevel", 11);
		getConfig().addDefault("goldWeaponLevel", 17);
		getConfig().addDefault("ironWeaponLevel", 22);
		getConfig().addDefault("diamondWeaponLevel", 30);
		
		getConfig().addDefault("bowLevel", 15);
		
		getConfig().addDefault("UseArmorControl", true);
		getConfig().addDefault("UseToolControl", false);
		getConfig().addDefault("UseWeaponControl", false);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
