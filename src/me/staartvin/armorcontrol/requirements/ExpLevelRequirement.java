package me.staartvin.armorcontrol.requirements;

import java.util.List;

import org.bukkit.entity.Player;

import me.staartvin.plugins.pluginlibrary.Library;

public class ExpLevelRequirement extends Requirement {

	private int xpLevel = -1;
	
	@Override
	public boolean setOptions(String... strings) {

		if (strings.length > 0) {
			xpLevel = Integer.parseInt(strings[0]);
		}

		return xpLevel != -1;
	}

	@Override
	public boolean meetsRequirement(Player player) {
		return player.getLevel() >= xpLevel;
	}

	@Override
	public List<Library> getRequiredLibraries() {
		return null;
	}

}
