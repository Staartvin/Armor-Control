package me.staartvin.armorcontrol.requirements;

import me.staartvin.armorcontrol.ArmorControl;

public class RequirementManager {

	private ArmorControl plugin;
	
	public RequirementManager(ArmorControl instance) {
		plugin = instance;
	}
	
	public Requirement createRequirement(RequirementType type) {
		Requirement r  = type.getRequirement();
		
		return r;
	}
	
	
	public RequirementType findConfigMatch(String string) {
		for (RequirementType type : RequirementType.values()) {
			if (type.getConfigString().equalsIgnoreCase(string)) return type;
		}
		
		return null;
	}
}
