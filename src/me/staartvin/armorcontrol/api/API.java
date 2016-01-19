package me.staartvin.armorcontrol.api;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.actionType;

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
	
	public boolean isAllowedToUse(Player player, ItemStack item, actionType action) {
		return player.getLevel() >= this.getLevelOfItem(item, action);
	}
	
	public int getLevelOfItem(ItemStack item, actionType action) {
		return plugin.getResManager().getRequiredLevel(item, action);
	}
	
	/**
	 * Gets a list of disabled worlds
	 * @return a list of disabled worlds
	 */
	public List<String> getDisabledWorlds() {
		return plugin.getConfigHandler().getDisabledWorlds();
	}
	
	/**
	 * Check if a world is disabled
	 * @param worldName World to check for
	 * @return true if disabled, false otherwise.
	 */
	public boolean isDisabledWorld(String worldName) {
		return this.getDisabledWorlds().contains(worldName);
	}
}
