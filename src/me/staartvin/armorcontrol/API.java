package me.staartvin.armorcontrol;

import java.util.List;

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
		return plugin.getMethods().checkLevel(player, mode, item);
	}
	
	/**
	 * Gets level required to use/wear item/armor
	 * @param mode Type of tool/armor (wood, stone, gold, iron, diamond, bow, leather, chain)
	 * @param item Type of item (weapon, tool, armor)
	 * @return Level required to use/wear item/armor.
	 */
	public int getLevelOfItem(String mode, String item) {
		return plugin.getMethods().findLevel(mode, item);
	}
	
	/**
	 * Gets a list of disabled worlds
	 * @return a list of disabled worlds
	 */
	public List<String> getDisabledWorlds() {
		return plugin.getWorldHandler().getDisabledWorlds();
	}
	
	/**
	 * Check if a world is disabled
	 * @param world World to check for
	 * @return true if disabled, false otherwise.
	 */
	public boolean isDisabledWorld(String world) {
		return plugin.getWorldHandler().isDisabled(world);
	}
}
