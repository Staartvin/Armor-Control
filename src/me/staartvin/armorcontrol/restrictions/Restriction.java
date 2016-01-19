package me.staartvin.armorcontrol.restrictions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import me.staartvin.armorcontrol.config.ConfigHandler.actionType;

public class Restriction {

	private HashMap<actionType, Integer> restrictions = new HashMap<actionType, Integer>();
	
	private List<String> disabledWorlds = new ArrayList<String>();
	
	private int dataValue = -1;
	private int itemID = 0;
	
	public Restriction() {}
	
	public int getActionLevel(actionType type) {
		return restrictions.get(type);
	}
	
	public void setActionLevel(actionType type, int level) {
		restrictions.put(type, level);
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
		
		for (Entry<actionType, Integer> entry: restrictions.entrySet()) {
			string.append(entry.getKey() + "=" + entry.getValue() + ", ");
		}
		
		string.append("disabled worlds: ");
		
		for (String worldName: disabledWorlds) {
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
		if (dataValue < 0 && data < 0) return true;
		
		return dataValue == data;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
}
