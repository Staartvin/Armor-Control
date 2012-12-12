package Staartvin.ArmorControl;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Staartvin
 *
 */
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
	API api = new API(this);
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(listener, this);
		methods.loadConfiguration();
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
	
	/**
	 * Get an instance of Armor Control.
	 * After an instance has been called, you can call all methods.
	 * @return API class which has all public methods.
	 */
	public API getInstance() {
		return api;
	}
}
