package Staartvin.ArmorControl;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Staartvin
 *
 */
public class Methods {
	
	ArmorControl plugin;
	
	public Methods (ArmorControl plugin) {
		this.plugin = plugin;
	}
	
	public String checkWeaponType(int ID) {
		if (ID == 268) return "wood";
		else if (ID == 272) return "stone";
		else if (ID == 267) return "iron";
		else if (ID == 283) return "gold";
		else if (ID == 276) return "diamond";
		else if (ID == 261) return "bow";
		else return "ID unknown";
	}
	
	protected void loadConfiguration() {
		plugin.getConfig().options().header(
				"ArmorControl v" + plugin.getDescription().getVersion() + " Config"
				+ "\nDo not touch the 'upgrade' part. It will mess up the config."
				+ "\nLatest CraftBukkit version tested on: 1.4.7-R1.0 #2570"
				+ "\n\nThanks for using Armor Control! Questions? http://dev.bukkit.org/server-mods/armor-control/");
		
		plugin.getConfig().addDefault("verboseLogging", true);
		plugin.getConfig().addDefault("Use custom IDs", false);
		
		plugin.getConfig().addDefault("Armor.Leather", 5);
		plugin.getConfig().addDefault("Armor.Chain", 11);
		plugin.getConfig().addDefault("Armor.Gold", 17);
		plugin.getConfig().addDefault("Armor.Iron", 22);
		plugin.getConfig().addDefault("Armor.Diamond", 30);
		
		plugin.getConfig().addDefault("Tools.Wood", 5);
		plugin.getConfig().addDefault("Tools.Stone", 11);
		plugin.getConfig().addDefault("Tools.Gold", 17);
		plugin.getConfig().addDefault("Tools.Iron", 22);
		plugin.getConfig().addDefault("Tools.Diamond", 30);
		
		plugin.getConfig().addDefault("Weapons.Wood", 5);
		plugin.getConfig().addDefault("Weapons.Stone", 11);
		plugin.getConfig().addDefault("Weapons.Gold", 17);
		plugin.getConfig().addDefault("Weapons.Iron", 22);
		plugin.getConfig().addDefault("Weapons.Diamond", 30);
		
		plugin.getConfig().addDefault("bowLevel", 15);
		
		plugin.getConfig().addDefault("UseArmorControl", true);
		plugin.getConfig().addDefault("UseToolControl", false);
		plugin.getConfig().addDefault("UseWeaponControl", false);
		plugin.getConfig().addDefault("Upgrade.12-to-13", true);
		
		plugin.customIDsConfig.addDefault("Custom IDs.Tekkit_Red_Matter_Sword.Data value", 27567);
		plugin.customIDsConfig.addDefault("Custom IDs.Tekkit_Red_Matter_Sword.Level", 10);
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		
		plugin.customIDsConfig.options().copyDefaults(true);
		plugin.getConfiguration().saveCustomIDsConfig();
	}
	
	protected void upgradeConfig(String versionfrom_to) {
		if (versionfrom_to.equals("1.2-to-1.3")) {
			
			if (!plugin.getConfig().getBoolean("Upgrade.12-to-13")) {
				return;
			}
			
			//Convert old config
			plugin.getConfig().set("Armor.Leather", plugin.getConfig().getInt("leatherArmorLevel"));
			plugin.getConfig().set("Armor.Chain", plugin.getConfig().getInt("chainArmorLevel"));
			plugin.getConfig().set("Armor.Gold", plugin.getConfig().getInt("goldArmorLevel"));
			plugin.getConfig().set("Armor.Iron", plugin.getConfig().getInt("ironArmorLevel"));
			plugin.getConfig().set("Armor.Diamond", plugin.getConfig().getInt("diamondArmorLevel"));
			
			plugin.getConfig().set("Tools.Wood", plugin.getConfig().getInt("woodToolLevel"));
			plugin.getConfig().set("Tools.Stone", plugin.getConfig().getInt("stoneToolLevel"));
			plugin.getConfig().set("Tools.Gold", plugin.getConfig().getInt("goldToolLevel"));
			plugin.getConfig().set("Tools.Iron", plugin.getConfig().getInt("ironToolLevel"));
			plugin.getConfig().set("Tools.Diamond", plugin.getConfig().getInt("diamondToolLevel"));
			
			plugin.getConfig().set("Weapons.Wood", plugin.getConfig().getInt("woodWeaponLevel"));
			plugin.getConfig().set("Weapons.Stone", plugin.getConfig().getInt("stoneWeaponLevel"));
			plugin.getConfig().set("Weapons.Gold", plugin.getConfig().getInt("goldWeaponLevel"));
			plugin.getConfig().set("Weapons.Iron", plugin.getConfig().getInt("ironWeaponLevel"));
			plugin.getConfig().set("Weapons.Diamond", plugin.getConfig().getInt("diamondWeaponLevel"));
			
			// Remove old options
			plugin.getConfig().set("leatherArmorLevel", null);
			plugin.getConfig().set("chainArmorLevel", null);
			plugin.getConfig().set("goldArmorLevel", null);
			plugin.getConfig().set("ironArmorLevel", null);
			plugin.getConfig().set("diamondArmorLevel", null);
			
			plugin.getConfig().set("woodToolLevel", null);
			plugin.getConfig().set("stoneToolLevel", null);
			plugin.getConfig().set("goldToolLevel", null);
			plugin.getConfig().set("ironToolLevel", null);
			plugin.getConfig().set("diamondToolLevel", null);
			
			plugin.getConfig().set("woodWeaponLevel", null);
			plugin.getConfig().set("stoneWeaponLevel", null);
			plugin.getConfig().set("goldWeaponLevel", null);
			plugin.getConfig().set("ironWeaponLevel", null);
			plugin.getConfig().set("diamondWeaponLevel", null);
			
			// Notice admin
			if (plugin.getConfig().getBoolean("verboseLogging")) {
				System.out.print("[ArmorControl] Old config has been converted to new config");
			}
			plugin.getConfig().set("Upgrade.12-to-13", false);
			
			plugin.saveConfig();
		}
	}
	/**
	 * Checks level of experience of a player against level limit
	 * @param player Player to check
	 * @param mode Type of tool (wood, stone, iron, diamond, gold, bow, leather, chain)
	 * @param item Type of item (weapon, tool or armor)
	 * @return True if player has enough xp
	 */
	public boolean checkLevel(Player player, String mode, String item) {
		if (item.equals("weapon")) {
			if (mode.equals("wood")) {if (player.getLevel() >= plugin.getLevels().getWoodWeaponLevel()) return true;}
			else if (mode.equals("stone")) {if (player.getLevel() >= plugin.getLevels().getStoneWeaponLevel()) return true;}
			else if (mode.equals("gold")) {if (player.getLevel() >= plugin.getLevels().getGoldWeaponLevel()) return true;}
			else if (mode.equals("iron")) {if (player.getLevel() >= plugin.getLevels().getIronWeaponLevel()) return true;}
			else if (mode.equals("diamond")) {if (player.getLevel() >= plugin.getLevels().getDiamondWeaponLevel()) return true;}
			else if (mode.equals("bow")) {if (player.getLevel() >= plugin.getLevels().getBowLevel()) return true;}
		}
		else if (item.equals("tool")) {
			if (mode.equals("wood")) {if (player.getLevel() >= plugin.getLevels().getWoodToolLevel()) return true;}
			else if (mode.equals("stone")) {if (player.getLevel() >= plugin.getLevels().getStoneToolLevel()) return true;}
			else if (mode.equals("gold")) {if (player.getLevel() >= plugin.getLevels().getGoldToolLevel()) return true;}
			else if (mode.equals("iron")) {if (player.getLevel() >= plugin.getLevels().getIronToolLevel()) return true;}
			else if (mode.equals("diamond")) {if (player.getLevel() >= plugin.getLevels().getDiamondToolLevel()) return true;}	
		}
		else if (item.equals("armor")) {
			if (mode.equals("leather")) {if (player.getLevel() >= plugin.getLevels().getLeatherArmorLevel()) return true;}
			else if (mode.equals("chain")) {if (player.getLevel() >= plugin.getLevels().getChainArmorLevel()) return true;}
			else if (mode.equals("gold")) {if (player.getLevel() >= plugin.getLevels().getGoldArmorLevel()) return true;}
			else if (mode.equals("iron")) {if (player.getLevel() >= plugin.getLevels().getIronArmorLevel()) return true;}
			else if (mode.equals("diamond")) {if (player.getLevel() >= plugin.getLevels().getDiamondArmorLevel()) return true;}	
		}
		else if (mode.equals("bow")) {if (player.getLevel() >= plugin.getLevels().getBowLevel()) return true;}
		
		return false;
	}
	
	/**
	 * Reads limits from configuration file.
	 */
	protected void readLimits() {
		try {
			plugin.getLevels().setLeatherArmorLevel(plugin.getConfig().getInt("Armor.Leather"));
			plugin.getLevels().setChainArmorLevel(plugin.getConfig().getInt("Armor.Chain"));
			plugin.getLevels().setGoldArmorLevel(plugin.getConfig().getInt("Armor.Gold"));
			plugin.getLevels().setIronArmorLevel(plugin.getConfig().getInt("Armor.Iron"));
			plugin.getLevels().setDiamondArmorLevel(plugin.getConfig().getInt("Armor.Diamond"));
			
			plugin.getLevels().setWoodToolLevel(plugin.getConfig().getInt("Tools.Wood"));
			plugin.getLevels().setStoneToolLevel(plugin.getConfig().getInt("Tools.Stone"));
			plugin.getLevels().setGoldToolLevel(plugin.getConfig().getInt("Tools.Gold"));
			plugin.getLevels().setIronToolLevel(plugin.getConfig().getInt("Tools.Iron"));
			plugin.getLevels().setDiamondToolLevel(plugin.getConfig().getInt("Tools.Diamond"));
			
			plugin.getLevels().setWoodWeaponLevel(plugin.getConfig().getInt("Weapons.Wood"));
			plugin.getLevels().setStoneWeaponLevel(plugin.getConfig().getInt("Weapons.Stone"));
			plugin.getLevels().setGoldWeaponLevel(plugin.getConfig().getInt("Weapons.Gold"));
			plugin.getLevels().setIronWeaponLevel(plugin.getConfig().getInt("Weapons.Iron"));
			plugin.getLevels().setDiamondWeaponLevel(plugin.getConfig().getInt("Weapons.Diamond"));

			plugin.getLevels().setBowLevel(plugin.getConfig().getInt("bowLevel"));
			
			if (plugin.getConfig().getBoolean("verboseLogging")) {
			System.out.print("[Armor Control] -------------------------------------------");
			System.out.print("[Armor Control] Leather Armor level is set to: " + plugin.getLevels().getLeatherArmorLevel());
			System.out.print("[Armor Control] Chain Armor level is set to: " + plugin.getLevels().getChainArmorLevel());
			System.out.print("[Armor Control] Gold Armor level is set to: " + plugin.getLevels().getGoldArmorLevel());
			System.out.print("[Armor Control] Iron Armor level is set to: " + plugin.getLevels().getIronArmorLevel());
			System.out.print("[Armor Control] Diamond Armor level is set to: " + plugin.getLevels().getDiamondArmorLevel());
			System.out.print("[Armor Control] -------------------------------------------");
			System.out.print("[Armor Control] Wooden Tool level is set to: " + plugin.getLevels().getWoodToolLevel());
			System.out.print("[Armor Control] Chain Tool level is set to: " + plugin.getLevels().getStoneToolLevel());
			System.out.print("[Armor Control] Gold Tool level is set to: " + plugin.getLevels().getGoldToolLevel());
			System.out.print("[Armor Control] Iron Tool level is set to: " + plugin.getLevels().getIronToolLevel());
			System.out.print("[Armor Control] Diamond Tool level is set to: " + plugin.getLevels().getDiamondToolLevel());
			System.out.print("[Armor Control] -------------------------------------------");
			System.out.print("[Armor Control] Wooden Weapon level is set to: " + plugin.getLevels().getWoodWeaponLevel());
			System.out.print("[Armor Control] Chain Weapon level is set to: " + plugin.getLevels().getStoneWeaponLevel());
			System.out.print("[Armor Control] Gold Weapon level is set to: " + plugin.getLevels().getGoldWeaponLevel());
			System.out.print("[Armor Control] Iron Weapon level is set to: " + plugin.getLevels().getIronWeaponLevel());
			System.out.print("[Armor Control] Diamond Weapon level is set to: " + plugin.getLevels().getDiamondWeaponLevel());
			System.out.print("[Armor Control] Bow level is set to: " + plugin.getLevels().getBowLevel());
			System.out.print("[Armor Control] -------------------------------------------");
			}	
		} catch (Exception e) {
			System.out.print("[Armor Control] Could not parse levels in config to integers!");
			System.out.print("[Armor Control] Switching to default levels!");
		}
	}
	
	/**
	 * Gets level required to use/wear item/armor
	 * @param mode Type of tool/armor (wood, stone, gold, iron, diamond, bow, leather, chain)
	 * @param item Type of item (weapon, tool, armor)
	 * @return Level required to use/wear item/armor.
	 */
	public int findLevel(String mode, String item) {
		if (item.equals("weapon")) {
			if (mode.equals("wood")) return plugin.getLevels().getWoodWeaponLevel();
			else if (mode.equals("stone")) return plugin.getLevels().getStoneWeaponLevel();
			else if (mode.equals("gold")) return plugin.getLevels().getGoldWeaponLevel();
			else if (mode.equals("iron")) return plugin.getLevels().getIronWeaponLevel();
			else if (mode.equals("diamond")) return plugin.getLevels().getDiamondWeaponLevel();
			else if (mode.equals("bow")) return plugin.getLevels().getBowLevel();
		}
		else if (item.equals("tool")) {
			if (mode.equals("wood")) return plugin.getLevels().getWoodToolLevel();
			else if (mode.equals("stone")) return plugin.getLevels().getStoneToolLevel();
			else if (mode.equals("gold")) return plugin.getLevels().getGoldToolLevel();
			else if (mode.equals("iron")) return plugin.getLevels().getIronToolLevel();
			else if (mode.equals("diamond")) return plugin.getLevels().getDiamondToolLevel();
		}
		else if (item.equals("armor")) {
			if (mode.equals("leather")) return plugin.getLevels().getLeatherArmorLevel();
			else if (mode.equals("chain")) return plugin.getLevels().getChainArmorLevel();
			else if (mode.equals("gold")) return plugin.getLevels().getGoldArmorLevel();
			else if (mode.equals("iron")) return plugin.getLevels().getIronArmorLevel();
			else if (mode.equals("diamond")) return plugin.getLevels().getDiamondArmorLevel();
		}
		return 0;
	}
	
	public void checkInventoryforArmor(Player player) {
	    if (plugin.getInventory().getHelmet() != null) {
	    	for (int i=0;i<plugin.getLevels().getHelmetIDs().length;i++) {
		    	if (plugin.getLevels().getHelmetIDs()[i] == plugin.getInventory().getHelmet().getType().getId()) {
		    		
		    		String mode;
		    		if (plugin.getInventory().getHelmet().getType().getId() == 298) {
		    			if (checkLevel(player, "leather", "armor")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.getInventory().getHelmet().getType().getId() == 302) {
		    			if (checkLevel(player, "chain", "armor")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.getInventory().getHelmet().getType().getId() == 306) {
		    			if (checkLevel(player, "iron", "armor")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.getInventory().getHelmet().getType().getId() == 310) {
		    			if (checkLevel(player, "diamond", "armor")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.getInventory().getHelmet().getType().getId() == 314) {
		    			if (checkLevel(player, "gold", "armor")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			break;
		    		}
		    		plugin.setArmorPart(new ItemStack(plugin.getInventory().getHelmet().getType().getId()));
		    		plugin.getArmorPart().setDurability(plugin.getInventory().getHelmet().getDurability());
		    		if (plugin.getInventory().getHelmet().getEnchantments().size() != 0) {
		    			try {
		    				plugin.getArmorPart().addEnchantments(plugin.getInventory().getHelmet().getEnchantments());
		    			} catch (Exception e) {
		    				plugin.getArmorPart().addUnsafeEnchantments(plugin.getInventory().getHelmet().getEnchantments());
		    			}
		    		}
		    		if (plugin.getInventory().firstEmpty() >= 0) {
		    			plugin.getInventory().addItem(plugin.getArmorPart());
		    		}
		    		else {
		    			player.getWorld().dropItem(player.getLocation(), plugin.getArmorPart());
		    		}
		    		
		    		plugin.getInventory().setHelmet(new ItemStack(Material.AIR, 1));
		    		player.sendMessage(ChatColor.RED + "You cannot wear this helmet. You must be at least level: " + findLevel(mode, "armor"));
		    		break;
		    	}
		    }
	    }
	    if (plugin.getInventory().getChestplate() != null) {
	    	for (int i=0;i<plugin.getLevels().getChestplateIDs().length;i++) {
		    	if (plugin.getLevels().getChestplateIDs()[i] == plugin.getInventory().getChestplate().getType().getId()) {
		    		
		    		String mode;
		    		if (plugin.getInventory().getChestplate().getType().getId() == 299) {
		    			if (checkLevel(player, "leather", "armor")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.getInventory().getChestplate().getType().getId() == 303) {
		    			if (checkLevel(player, "chain", "armor")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.getInventory().getChestplate().getType().getId() == 307) {
		    			if (checkLevel(player, "iron", "armor")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.getInventory().getChestplate().getType().getId() == 311) {
		    			if (checkLevel(player, "diamond", "armor")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.getInventory().getChestplate().getType().getId() == 315) {
		    			if (checkLevel(player, "gold", "armor")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			break;
		    		}
		    		plugin.setArmorPart(new ItemStack(plugin.getInventory().getChestplate().getType().getId()));
		    		plugin.getArmorPart().setDurability(plugin.getInventory().getChestplate().getDurability());
		    		if (plugin.getInventory().getChestplate().getEnchantments().size() != 0) {
		    			try {
		    				plugin.getArmorPart().addEnchantments(plugin.getInventory().getChestplate().getEnchantments());
		    			} catch (Exception e) {
		    				plugin.getArmorPart().addUnsafeEnchantments(plugin.getInventory().getChestplate().getEnchantments());
		    			}
		    		}
		    		if (plugin.getInventory().firstEmpty() >= 0) {
		    			plugin.getInventory().addItem(plugin.getArmorPart());
		    		}
		    		else {
		    			player.getWorld().dropItem(player.getLocation(), plugin.getArmorPart());
		    		}
		    		plugin.getInventory().setChestplate(new ItemStack(Material.AIR, 1));
		    		player.sendMessage(ChatColor.RED + "You cannot wear this chestplate. You must be at least level: " + findLevel(mode, "armor"));
		    		break;
		    		}
		    	}
		    }
	    if (plugin.getInventory().getLeggings() != null) {
	    	for (int i=0;i<plugin.getLevels().getLeggingIDs().length;i++) {
		    	if (plugin.getLevels().getLeggingIDs()[i] == plugin.getInventory().getLeggings().getType().getId()) {
		    		
		    		String mode;
		    		if (plugin.getInventory().getLeggings().getType().getId() == 300) {
		    			if (checkLevel(player, "leather", "armor")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.getInventory().getLeggings().getType().getId() == 304) {
		    			if (checkLevel(player, "chain", "armor")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.getInventory().getLeggings().getType().getId() == 308) {
		    			if (checkLevel(player, "iron", "armor")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.getInventory().getLeggings().getType().getId() == 312) {
		    			if (checkLevel(player, "diamond", "armor")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.getInventory().getLeggings().getType().getId() == 316) {
		    			if (checkLevel(player, "gold", "armor")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			break;
		    		}
		    		plugin.setArmorPart(new ItemStack(plugin.getInventory().getLeggings().getType().getId()));
		    		plugin.getArmorPart().setDurability(plugin.getInventory().getLeggings().getDurability());
		    		if (plugin.getInventory().getLeggings().getEnchantments().size() != 0) {
		    			try {
		    				plugin.getArmorPart().addEnchantments(plugin.getInventory().getLeggings().getEnchantments());
		    			} catch (Exception e) {
		    				plugin.getArmorPart().addUnsafeEnchantments(plugin.getInventory().getLeggings().getEnchantments());
		    			}
		    		}
		    		if (plugin.getInventory().firstEmpty() >= 0) {
		    			plugin.getInventory().addItem(plugin.getArmorPart());
		    		}
		    		else {
		    			player.getWorld().dropItem(player.getLocation(), plugin.getArmorPart());
		    		}
		    		plugin.getInventory().setLeggings(new ItemStack(Material.AIR, 1));
		    		player.sendMessage(ChatColor.RED + "You cannot wear these leggings. You must be at least level: " + findLevel(mode, "armor"));
		    		break;
		    	}
		    }
	    }
	    if (plugin.getInventory().getBoots() != null) {
	    	for (int i=0;i<plugin.getLevels().getBootIDs().length;i++) {
		    	if (plugin.getLevels().getBootIDs()[i] == plugin.getInventory().getBoots().getType().getId()) {

		    		String mode;
		    		if (plugin.getInventory().getBoots().getType().getId() == 301) {
		    			if (checkLevel(player, "leather", "armor")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.getInventory().getBoots().getType().getId() == 305) {
		    			if (checkLevel(player, "chain", "armor")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.getInventory().getBoots().getType().getId() == 309) {
		    			if (checkLevel(player, "iron", "armor")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.getInventory().getBoots().getType().getId() == 313) {
		    			if (checkLevel(player, "diamond", "armor")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.getInventory().getBoots().getType().getId() == 317) {
		    			if (checkLevel(player, "gold", "armor")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			break;
		    		}
		    		plugin.setArmorPart(new ItemStack(plugin.getInventory().getBoots().getType().getId()));
		    		plugin.getArmorPart().setDurability(plugin.getInventory().getBoots().getDurability());
		    		if (plugin.getInventory().getBoots().getEnchantments().size() != 0) {
		    			try {
		    				plugin.getArmorPart().addEnchantments(plugin.getInventory().getBoots().getEnchantments());
		    			} catch (Exception e) {
		    				plugin.getArmorPart().addUnsafeEnchantments(plugin.getInventory().getBoots().getEnchantments());
		    			}
		    		}
		    		if (plugin.getInventory().firstEmpty() >= 0) {
		    			plugin.getInventory().addItem(plugin.getArmorPart());
		    		}
		    		else {
		    			player.getWorld().dropItem(player.getLocation(), plugin.getArmorPart());
		    		}
		    		plugin.getInventory().setBoots(new ItemStack(Material.AIR, 1));
		    		player.sendMessage(ChatColor.RED + "You cannot wear these boots. You must be at least level: " + findLevel(mode, "armor"));
		    		break;
		    	}
		    }
		}
	}
	
	public boolean isNotAllowedCustomID(Integer IDinHand, Player player) {
	    for (String id: plugin.getCustomIDClass().getCustomIDs()) {
	    	String[] tempArray = id.split(":");
	    	String name = tempArray[1];
	    	String DV = tempArray[0];
	    	int ID = 0;
	    	try {
	    		ID = Integer.parseInt(DV);
	    	} catch(Exception e) {
	    		System.out.print("[ArmorControl] ERROR OCCURED: CUSTOM ID '" + name + "' DOES NOT HAVE A VALID DATA VALUE! CHECK YOUR CONFIG!");
	    		return false;
	    	}
	    	if (ID == IDinHand) {
	    		if (!(player.getLevel() >= plugin.getCustomIDClass().findLevel(name))) {
	    			player.sendMessage(ChatColor.RED + "You cannot use this! You must be at least level: " + plugin.getCustomIDClass().findLevel(name));
		    		return true;
	    		}
	    		return false;
	    	}
	    	continue;
	    }
	    return false;
	}
}
