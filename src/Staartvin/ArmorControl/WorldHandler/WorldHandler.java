package Staartvin.ArmorControl.WorldHandler;

import java.util.List;

import Staartvin.ArmorControl.ArmorControl;

public class WorldHandler {

	ArmorControl plugin;
	
	public WorldHandler(ArmorControl instance) {
		plugin = instance;
	}
	
	public List<String> getDisabledWorlds() {
		return plugin.getConfig().getStringList("DisabledWorlds");
	}
	
	public boolean isDisabled(String worldName) {
		List<String> disabledWorlds = getDisabledWorlds();
		
		for (String world: disabledWorlds) {
			if (world.equalsIgnoreCase(worldName)) {
				return true;
			}
		}
		return false;
	}
	
}
