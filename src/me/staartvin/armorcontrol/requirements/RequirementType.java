package me.staartvin.armorcontrol.requirements;

public enum RequirementType {

	EXP_LEVEL("exp level", new ExpLevelRequirement());
	
	
	private String configString = null;
	private Requirement req = null;
	
	private RequirementType(String configString, Requirement req) {
		this.setConfigString(configString);
		this.setRequirement(req);;
	}

	public String getConfigString() {
		return configString;
	}

	public void setConfigString(String configString) {
		this.configString = configString;
	}

	public Requirement getRequirement() {
		return req;
	}

	public void setRequirement(Requirement req) {
		this.req = req;
	}
}