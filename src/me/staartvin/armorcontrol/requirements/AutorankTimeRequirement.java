package me.staartvin.armorcontrol.requirements;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.hooks.AutorankHook;

public class AutorankTimeRequirement extends Requirement {

	private int time = -1;

	@Override
	public boolean setOptions(String... strings) {

		if (strings.length > 0) {
			time = Integer.parseInt(strings[0]);
		}

		return time != -1;
	}

	@Override
	public boolean meetsRequirement(Player player) {
		AutorankHook hook = (AutorankHook) this.getArmorControl().getPluginLibraryHook()
				.getLibraryHook(Library.AUTORANK);
		
		if (!this.getArmorControl().getPluginLibraryHook().isLoaded(Library.AUTORANK)) return false;

		return hook.getLocalPlayTime(player.getUniqueId()) >= time;
	}

	@SuppressWarnings("serial")
	@Override
	public List<Library> getRequiredLibraries() {	
		return new ArrayList<Library>() {{
		    add(Library.AUTORANK);
		}};
	}

}
