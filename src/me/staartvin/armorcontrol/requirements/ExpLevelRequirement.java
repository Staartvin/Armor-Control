package me.staartvin.armorcontrol.requirements;

import org.bukkit.entity.Player;

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

}
