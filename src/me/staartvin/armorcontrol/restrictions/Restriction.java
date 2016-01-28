package me.staartvin.armorcontrol.restrictions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.entity.Player;

import me.staartvin.armorcontrol.requirements.Requirement;
import me.staartvin.armorcontrol.restrictions.RestrictionsManager.actionType;

public class Restriction {

	private HashMap<actionType, List<Requirement>> restrictions = new HashMap<actionType, List<Requirement>>();

	private List<String> disabledWorlds = new ArrayList<String>();

	private int dataValue = -1;
	private int itemID = 0;

	public List<Requirement> getRequirements(actionType type) {
		if (!restrictions.containsKey(type))
			return new ArrayList<Requirement>();
		
		return restrictions.get(type);
	}
	
	public void setRequirements(actionType type, List<Requirement> reqs) {
		this.restrictions.put(type, reqs);
	}

	public List<String> getDisabledWorlds() {
		return disabledWorlds;
	}

	public boolean isDisabledWorld(String worldName) {
		return this.getDisabledWorlds().contains(worldName);
	}

	public void addDisabledWorld(String worldName) {
		disabledWorlds.add(worldName);
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("Restriction [");

		for (Entry<actionType, List<Requirement>> entry : restrictions.entrySet()) {
			string.append(entry.getKey() + "=" + entry.getValue() + ", ");
		}

		string.append("disabled worlds: ");

		for (String worldName : disabledWorlds) {
			string.append(worldName + ",");
		}

		string.append("]");

		return string.toString();
	}

	public int getDataValue() {
		return dataValue;
	}

	public void setDataValue(int dataValue) {
		this.dataValue = dataValue;
	}

	public boolean hasEqualDataValue(int data) {
		if (dataValue < 0 && data < 0)
			return true;

		return dataValue == data;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public boolean meetsAllRequirements(Player player, actionType type) {
		// If none of the requirements failed, they must all have been met.
		return this.getFailedRequirements(player, type).isEmpty();
	}
	
	public List<Requirement> getFailedRequirements(Player player, actionType type) {
		List<Requirement> failed = new ArrayList<Requirement>();
		
		for (Requirement req: restrictions.get(type)) {
			if (!req.meetsRequirement(player)) {
				// This requirement was not met, so it has been failed.
				// We can now add that to the list of failed requirements
				failed.add(req);
			}
		}
		
		return failed;
	}
}
