package me.staartvin.armorcontrol.requirements;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.plugins.pluginlibrary.Library;

public abstract class Requirement {
	
	private String description = null;

	public abstract boolean setOptions(String... strings);
	
	public abstract boolean meetsRequirement(Player player);
	
	public void setDescription(String desc) {
		description = desc;
	}
	
	public String getDescription() {
		return description;
	}
	
	public ArmorControl getArmorControl() {
		return (ArmorControl) Bukkit.getServer().getPluginManager().getPlugin("ArmorControl");
	}
	
	/**
	 * A list of libraries that are required for this requirement to run.
	 */
	public abstract List<Library> getRequiredLibraries();
}
