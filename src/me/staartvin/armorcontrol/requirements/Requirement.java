package me.staartvin.armorcontrol.requirements;

import org.bukkit.entity.Player;

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
}
