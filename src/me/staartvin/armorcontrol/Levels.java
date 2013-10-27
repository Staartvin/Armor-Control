package me.staartvin.armorcontrol;

public class Levels {

	ArmorControl plugin;
	
	public Levels (ArmorControl plugin) {
		this.plugin = plugin;
	}
	
	public int[] getHelmetIDs() {
		return helmetIDs;
	}

	public void setHelmetIDs(int[] helmetIDs) {
		this.helmetIDs = helmetIDs;
	}

	public int[] getChestplateIDs() {
		return chestplateIDs;
	}

	public void setChestplateIDs(int[] chestplateIDs) {
		this.chestplateIDs = chestplateIDs;
	}

	public int[] getLeggingIDs() {
		return leggingIDs;
	}

	public void setLeggingIDs(int[] leggingIDs) {
		this.leggingIDs = leggingIDs;
	}

	public int[] getBootIDs() {
		return bootIDs;
	}

	public void setBootIDs(int[] bootIDs) {
		this.bootIDs = bootIDs;
	}

	public int[] getWoodToolsIDs() {
		return woodToolsIDs;
	}

	public void setWoodToolsIDs(int[] woodToolsIDs) {
		this.woodToolsIDs = woodToolsIDs;
	}

	public int[] getStoneToolsIDs() {
		return stoneToolsIDs;
	}

	public void setStoneToolsIDs(int[] stoneToolsIDs) {
		this.stoneToolsIDs = stoneToolsIDs;
	}

	public int[] getIronToolsIDs() {
		return ironToolsIDs;
	}

	public void setIronToolsIDs(int[] ironToolsIDs) {
		this.ironToolsIDs = ironToolsIDs;
	}

	public int[] getGoldToolsIDs() {
		return goldToolsIDs;
	}

	public void setGoldToolsIDs(int[] goldToolsIDs) {
		this.goldToolsIDs = goldToolsIDs;
	}

	public int[] getDiamondToolsIDs() {
		return diamondToolsIDs;
	}

	public void setDiamondToolsIDs(int[] diamondToolsIDs) {
		this.diamondToolsIDs = diamondToolsIDs;
	}

	public int[] getWeaponIDs() {
		return weaponIDs;
	}

	public void setWeaponIDs(int[] weaponIDs) {
		this.weaponIDs = weaponIDs;
	}

	public int getLeatherArmorLevel() {
		return leatherArmorLevel;
	}

	public void setLeatherArmorLevel(int leatherArmorLevel) {
		this.leatherArmorLevel = leatherArmorLevel;
	}

	public int getChainArmorLevel() {
		return chainArmorLevel;
	}

	public void setChainArmorLevel(int chainArmorLevel) {
		this.chainArmorLevel = chainArmorLevel;
	}

	public int getGoldArmorLevel() {
		return goldArmorLevel;
	}

	public void setGoldArmorLevel(int goldArmorLevel) {
		this.goldArmorLevel = goldArmorLevel;
	}

	public int getIronArmorLevel() {
		return ironArmorLevel;
	}

	public void setIronArmorLevel(int ironArmorLevel) {
		this.ironArmorLevel = ironArmorLevel;
	}

	public int getDiamondArmorLevel() {
		return diamondArmorLevel;
	}

	public void setDiamondArmorLevel(int diamondArmorLevel) {
		this.diamondArmorLevel = diamondArmorLevel;
	}

	public int getWoodToolLevel() {
		return woodToolLevel;
	}

	public void setWoodToolLevel(int woodToolLevel) {
		this.woodToolLevel = woodToolLevel;
	}

	public int getStoneToolLevel() {
		return stoneToolLevel;
	}

	public void setStoneToolLevel(int stoneToolLevel) {
		this.stoneToolLevel = stoneToolLevel;
	}

	public int getIronToolLevel() {
		return ironToolLevel;
	}

	public void setIronToolLevel(int ironToolLevel) {
		this.ironToolLevel = ironToolLevel;
	}

	public int getGoldToolLevel() {
		return goldToolLevel;
	}

	public void setGoldToolLevel(int goldToolLevel) {
		this.goldToolLevel = goldToolLevel;
	}

	public int getDiamondToolLevel() {
		return diamondToolLevel;
	}

	public void setDiamondToolLevel(int diamondToolLevel) {
		this.diamondToolLevel = diamondToolLevel;
	}

	public int getWoodWeaponLevel() {
		return woodWeaponLevel;
	}

	public void setWoodWeaponLevel(int woodWeaponLevel) {
		this.woodWeaponLevel = woodWeaponLevel;
	}

	public int getStoneWeaponLevel() {
		return stoneWeaponLevel;
	}

	public void setStoneWeaponLevel(int stoneWeaponLevel) {
		this.stoneWeaponLevel = stoneWeaponLevel;
	}

	public int getGoldWeaponLevel() {
		return goldWeaponLevel;
	}

	public void setGoldWeaponLevel(int goldWeaponLevel) {
		this.goldWeaponLevel = goldWeaponLevel;
	}

	public int getIronWeaponLevel() {
		return ironWeaponLevel;
	}

	public void setIronWeaponLevel(int ironWeaponLevel) {
		this.ironWeaponLevel = ironWeaponLevel;
	}

	public int getDiamondWeaponLevel() {
		return diamondWeaponLevel;
	}

	public void setDiamondWeaponLevel(int diamondWeaponLevel) {
		this.diamondWeaponLevel = diamondWeaponLevel;
	}

	public int getBowLevel() {
		return bowLevel;
	}

	public void setBowLevel(int bowLevel) {
		this.bowLevel = bowLevel;
	}

	private int[] helmetIDs = {298, 302, 306, 310, 314};
	private int[] chestplateIDs = {299, 303, 307, 311, 315};
	private int[] leggingIDs = {300, 304, 308, 312, 316};
	private int[] bootIDs = {301, 305, 309, 313, 317};
	
	private int[] woodToolsIDs = {269, 270, 271, 290};
	private int[] stoneToolsIDs = {273, 274, 275, 291};
	private int[] ironToolsIDs = {256, 257, 258, 292};
	private int[] goldToolsIDs = {284, 285, 286, 294};
	private int[] diamondToolsIDs = {277, 278, 279, 293};
	
	private int[] weaponIDs = {267, 268, 272, 276, 283, 261};
	
	// Tekkit support
	//private int[] tekkitHelmetArmor = {27550, 27576, 27580, 30116, 30174, 30178, 30195};
	//private int[] tekkitChestPlateArmor = {27549, 27575, 27579, 30173, 30177, 30194};
	//private int[] tekkitLeggingsArmor = {30172, 30176, 27551, 27577, 27581, 30193};
	//private int[] tekkitBootsArmor = {27552, 27578, 27582, 30171, 30175, 30192, 30211};
	//private int[] tekkitSwords = {1275, 1276, 1277, 27546, 27567, 30198};
	
	private int leatherArmorLevel = 5;
	private int chainArmorLevel = 11;
	private int goldArmorLevel = 17;
	private int ironArmorLevel = 22;
	private int diamondArmorLevel = 30;
	
	private int woodToolLevel = 5;
	private int stoneToolLevel = 11;
	private int goldToolLevel = 17;
	private int ironToolLevel = 22;
	private int diamondToolLevel = 30;
	
	private int woodWeaponLevel = 5;
	private int stoneWeaponLevel = 11;
	private int goldWeaponLevel = 17;
	private int ironWeaponLevel = 22;
	private int diamondWeaponLevel = 30;
	
	private int bowLevel = 15;
}
