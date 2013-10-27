package me.staartvin.armorcontrol;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;

public class CustomIDs {

	ArmorControl plugin;

	public CustomIDs(ArmorControl plugin) {
		this.plugin = plugin;
	}

	private List<String> customIDs = new ArrayList<String>();
	private List<Integer> customDataValues = new ArrayList<Integer>();

	protected void loadCustomIDs() {
		if (!plugin.getConfig().getBoolean("Use custom IDs"))
			return;
		FileConfiguration config = plugin.customIDsConfig;
		Set<String> keys = plugin.customIDsConfig.getConfigurationSection(
				"Custom IDs").getKeys(false);
		Object[] tempArray = keys.toArray();

		for (int i = 0; i < keys.size(); i++) {
			String result = "";
			result = result.concat(config.getInt("Custom IDs." + tempArray[i]
					+ ".Data value")
					+ ":" + tempArray[i]);
			customIDs.add(result);
			result = "";
			int intResult = config.getInt("Custom IDs." + tempArray[i]
					+ ".Data value");
			customDataValues.add(intResult);
		}

		System.out.println("[" + plugin.getDescription().getName()
				+ "] Custom ID config loaded!");
	}

	public List<Integer> getCustomDataValues() {
		return customDataValues;
	}

	public List<String> getCustomIDs() {
		return customIDs;
	}

	public int findLevel(String name) {
		FileConfiguration config = plugin.customIDsConfig;
		return config.getInt("Custom IDs." + name + ".Level");
	}

	/*	protected String getCustomID(String ID) {
			if (!plugin.getConfig().getBoolean("Use custom IDs")) return null;
			if (customIDs.size() == 0) return null;
			List<String> customIDs = this.customIDs;
			
			for (int i=0;i<customIDs.size();i++){
				if (customIDs.get(i).contains(ID)) {
					String[] tempArray = {};
					tempArray = customIDs.get(i).split(":");
					if (tempArray.length != 3) return null;
					return tempArray[2];
				}
			}
			return null;
		} */
}
