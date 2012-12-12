package Staartvin.ArmorControl;

import org.bukkit.entity.Player;

public class API {

	ArmorControl plugin;
	
	public API (ArmorControl plugin) {
		this.plugin = plugin;
	}
	
	public String getName() {
		return plugin.getName();
	}
	
	public String getVersion() {
		return plugin.getDescription().getVersion();
	}
	
	/**
	 * Checks level of experience of a player against level limit
	 * @param player Player to check
	 * @param mode Type of tool (wood, stone, iron, diamond, gold, bow, leather, chain)
	 * @param item Type of item (weapon, tool or armor)
	 * @return True if player has enough xp
	 */
	public boolean isAllowedToUse(Player player, String mode, String item) {
		return plugin.methods.checkLevel(player, mode, item);
	}
	
	/**
	 * Gets level required to use/wear item/armor
	 * @param mode Type of tool/armor (wood, stone, gold, iron, diamond, bow, leather, chain)
	 * @param item Type of item (weapon, tool, armor)
	 * @return Level required to use/wear item/armor.
	 */
	public int getLevelOfItem(String mode, String item) {
		return plugin.methods.findLevel(mode, item);
	}
	
	/**
	 * Get the level needed to wear a certain piece of armor.
	 * @param type Type of armor (leather, chain, gold, iron, diamond)
	 * @return Level needed to wear armor. Returns -1 if level is not found.
	 */
	public int getArmorLevel(String type) {
		if (type.equals("leather")) {return plugin.leatherArmorLevel;}
		else if (type.equals("chain")) {return plugin.chainArmorLevel;}
		else if (type.equals("gold")) {return plugin.goldArmorLevel;}
		else if (type.equals("iron")) {return plugin.ironArmorLevel;}
		else if (type.equals("diamond")) {return plugin.diamondArmorLevel;}
		else {return -1;}
	}
	/**
	 * Get the level needed to use a certain tool.
	 * @param type Type of tool (wood, stone, gold, iron, diamond)
	 * @return Level needed to use tool. Returns -1 if level is not found.
	 */
	public int getToolLevel(String type) {
		if (type.equals("wood")) {return plugin.woodToolLevel;}
		else if (type.equals("stone")) {return plugin.stoneToolLevel;}
		else if (type.equals("gold")) {return plugin.goldToolLevel;}
		else if (type.equals("iron")) {return plugin.ironToolLevel;}
		else if (type.equals("diamond")) {return plugin.diamondToolLevel;}
		else {return -1;}
	}
	/**
	 * Get the level needed to use a certain tool.
	 * @param type Type of tool (wood, stone, gold, iron, diamond)
	 * @return Level needed to use tool. Returns -1 if level is not found.
	 */
	public int getWeaponLevel(String type) {
		if (type.equals("wood")) {return plugin.woodWeaponLevel;}
		else if (type.equals("stone")) {return plugin.stoneWeaponLevel;}
		else if (type.equals("gold")) {return plugin.goldWeaponLevel;}
		else if (type.equals("iron")) {return plugin.ironWeaponLevel;}
		else if (type.equals("diamond")) {return plugin.diamondWeaponLevel;}
		else {return -1;}
	}
}
