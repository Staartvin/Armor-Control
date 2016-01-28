package me.staartvin.armorcontrol.requirements;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.hooks.AutorankHook;
import me.staartvin.plugins.pluginlibrary.hooks.McMMOHook;

public class McMMOSkillRequirement extends Requirement {

	private int level = -1;
	private String skillName = null;

	@Override
	public boolean setOptions(String... strings) {

		if (strings.length > 0) {
			skillName = strings[0];
		}
		
		if (strings.length > 1) {
			level = Integer.parseInt(strings[1]);
		}

		return level != -1 && skillName != null;
	}

	@Override
	public boolean meetsRequirement(Player player) {
		McMMOHook hook = (McMMOHook) this.getArmorControl().getPluginLibraryHook()
				.getLibraryHook(Library.MCMMO);
		
		if (!this.getArmorControl().getPluginLibraryHook().isLoaded(Library.MCMMO)) return false;

		return hook.getLevel(player, skillName) >= level;
	}

	@SuppressWarnings("serial")
	@Override
	public List<Library> getRequiredLibraries() {	
		return new ArrayList<Library>() {{
		    add(Library.MCMMO);
		}};
	}

}
