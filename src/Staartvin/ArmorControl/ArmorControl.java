package Staartvin.ArmorControl;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Staartvin
 *
 */
public class ArmorControl extends JavaPlugin {

	protected int[] helmetIDs = {298, 302, 306, 310, 314};
	protected int[] chestplateIDs = {299, 303, 307, 311, 315};
	protected int[] leggingIDs = {300, 304, 308, 312, 316};
	protected int[] bootIDs = {301, 305, 309, 313, 317};
	
	protected int[] woodToolsIDs = {269, 270, 271, 290};
	protected int[] stoneToolsIDs = {273, 274, 275, 291};
	protected int[] ironToolsIDs = {256, 257, 258, 292};
	protected int[] goldToolsIDs = {284, 285, 286, 294};
	protected int[] diamondToolsIDs = {277, 278, 279, 293};
	
	protected int[] weaponIDs = {267, 268, 272, 276, 283, 261};
	
	// Tekkit support
	protected int[] tekkitHelmetArmor = {27550, 27576, 27580, 30116, 30174, 30178, 30195};
	protected int[] tekkitChestPlateArmor = {27549, 27575, 27579, 30173, 30177, 30194};
	protected int[] tekkitLeggingsArmor = {30172, 30176, 27551, 27577, 27581, 30193};
	protected int[] tekkitBootsArmor = {27552, 27578, 27582, 30171, 30175, 30192, 30211};
	
	protected int[] tekkitSwords = {1275, 1276, 1277, 27546, 27567, 30198};
	
	protected ItemStack armorPart;
	protected ItemStack air = new ItemStack(Material.AIR, 1);
	protected PlayerInventory inv;
	
	protected int leatherArmorLevel = 5;
	protected int chainArmorLevel = 11;
	protected int goldArmorLevel = 17;
	protected int ironArmorLevel = 22;
	protected int diamondArmorLevel = 30;
	
	protected int woodToolLevel = 5;
	protected int stoneToolLevel = 11;
	protected int goldToolLevel = 17;
	protected int ironToolLevel = 22;
	protected int diamondToolLevel = 30;
	
	protected int woodWeaponLevel = 5;
	protected int stoneWeaponLevel = 11;
	protected int goldWeaponLevel = 17;
	protected int ironWeaponLevel = 22;
	protected int diamondWeaponLevel = 30;
	
	protected int bowLevel = 15;
	
	protected Listeners listener = new Listeners(this);
	protected Methods methods = new Methods(this);
	protected API api = new API(this);
	
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
