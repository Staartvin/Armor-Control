package me.staartvin.armorcontrol.hooks;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.plugins.pluginlibrary.Library;
import me.staartvin.plugins.pluginlibrary.PluginLibrary;
import me.staartvin.plugins.pluginlibrary.hooks.LibraryHook;

public class PluginLibraryHook {

	private ArmorControl plugin;
	
	public PluginLibraryHook(ArmorControl instance) {
		this.plugin = instance;
	}
	
	public LibraryHook getLibraryHook(Library lib) {
		return PluginLibrary.getLibrary(lib);
	}
	
	public boolean isLoaded(Library lib) {
		try {
			return PluginLibrary.isLibraryLoaded(lib);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
