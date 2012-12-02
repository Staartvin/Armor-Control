package Staartvin.ArmorControl;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Vincent
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
	
	/**
	 * Checks level of experience of a player against level limit
	 * @param player Player to check
	 * @param mode Type of tool (wood, stone, iron, diamond, gold, bow, leather, chain)
	 * @param item Type of item (weapon, tool or armor)
	 * @return True if player has enough xp
	 */
	public boolean checkLevel(Player player, String mode, String item) {
		if (item.equals("weapon")) {
			if (mode.equals("wood")) {if (player.getLevel() >= plugin.woodWeaponLevel) return true;}
			else if (mode.equals("stone")) {if (player.getLevel() >= plugin.stoneWeaponLevel) return true;}
			else if (mode.equals("gold")) {if (player.getLevel() >= plugin.goldWeaponLevel) return true;}
			else if (mode.equals("iron")) {if (player.getLevel() >= plugin.ironWeaponLevel) return true;}
			else if (mode.equals("diamond")) {if (player.getLevel() >= plugin.diamondWeaponLevel) return true;}
			else if (mode.equals("bow")) {if (player.getLevel() >= plugin.bowLevel) return true;}
		}
		else if (item.equals("tool")) {
			if (mode.equals("wood")) {if (player.getLevel() >= plugin.woodToolLevel) return true;}
			else if (mode.equals("stone")) {if (player.getLevel() >= plugin.stoneToolLevel) return true;}
			else if (mode.equals("gold")) {if (player.getLevel() >= plugin.goldToolLevel) return true;}
			else if (mode.equals("iron")) {if (player.getLevel() >= plugin.ironToolLevel) return true;}
			else if (mode.equals("diamond")) {if (player.getLevel() >= plugin.diamondToolLevel) return true;}	
		}
		else if (item.equals("armor")) {
			if (mode.equals("leather")) {if (player.getLevel() >= plugin.leatherArmorLevel) return true;}
			else if (mode.equals("chain")) {if (player.getLevel() >= plugin.chainArmorLevel) return true;}
			else if (mode.equals("gold")) {if (player.getLevel() >= plugin.goldArmorLevel) return true;}
			else if (mode.equals("iron")) {if (player.getLevel() >= plugin.ironArmorLevel) return true;}
			else if (mode.equals("diamond")) {if (player.getLevel() >= plugin.diamondArmorLevel) return true;}	
		}
		else if (mode.equals("bow")) {if (player.getLevel() >= plugin.bowLevel) return true;}
		
		return false;
	}
	
	/**
	 * Reads limits from configuration file.
	 */
	public void readLimits() {
		try {
			plugin.leatherArmorLevel = plugin.getConfig().getInt("leatherArmorLevel");
			plugin.chainArmorLevel = plugin.getConfig().getInt("chainArmorLevel");
			plugin.goldArmorLevel = plugin.getConfig().getInt("goldArmorLevel");
			plugin.ironArmorLevel = plugin.getConfig().getInt("ironArmorLevel");
			plugin.diamondArmorLevel = plugin.getConfig().getInt("diamondArmorLevel");
			
			plugin.woodToolLevel = plugin.getConfig().getInt("woodToolLevel");
			plugin.stoneToolLevel = plugin.getConfig().getInt("stoneToolLevel");
			plugin.goldToolLevel = plugin.getConfig().getInt("goldToolLevel");
			plugin.ironToolLevel = plugin.getConfig().getInt("ironToolLevel");
			plugin.diamondToolLevel = plugin.getConfig().getInt("diamondToolLevel");
			
			plugin.woodWeaponLevel = plugin.getConfig().getInt("woodWeaponLevel");
			plugin.stoneWeaponLevel = plugin.getConfig().getInt("stoneWeaponLevel");
			plugin.goldWeaponLevel = plugin.getConfig().getInt("goldWeaponLevel");
			plugin.ironWeaponLevel = plugin.getConfig().getInt("ironWeaponLevel");
			plugin.diamondWeaponLevel = plugin.getConfig().getInt("diamondWeaponLevel");

			plugin.bowLevel = plugin.getConfig().getInt("bowLevel");
			
			if (plugin.getConfig().getBoolean("verboseLogging")) {
			System.out.print("[Armor Control] -------------------------------------------");
			System.out.print("[Armor Control] Leather Armor level is set to: " + plugin.leatherArmorLevel);
			System.out.print("[Armor Control] Chain Armor level is set to: " + plugin.chainArmorLevel);
			System.out.print("[Armor Control] Gold Armor level is set to: " + plugin.goldArmorLevel);
			System.out.print("[Armor Control] Iron Armor level is set to: " + plugin.ironArmorLevel);
			System.out.print("[Armor Control] Diamond Armor level is set to: " + plugin.diamondArmorLevel);
			System.out.print("[Armor Control] -------------------------------------------");
			System.out.print("[Armor Control] Wooden Tool level is set to: " + plugin.woodToolLevel);
			System.out.print("[Armor Control] Chain Tool level is set to: " + plugin.stoneToolLevel);
			System.out.print("[Armor Control] Gold Tool level is set to: " + plugin.goldToolLevel);
			System.out.print("[Armor Control] Iron Tool level is set to: " + plugin.ironToolLevel);
			System.out.print("[Armor Control] Diamond Tool level is set to: " + plugin.diamondToolLevel);
			System.out.print("[Armor Control] -------------------------------------------");
			System.out.print("[Armor Control] Wooden Weapon level is set to: " + plugin.woodWeaponLevel);
			System.out.print("[Armor Control] Chain Weapon level is set to: " + plugin.stoneWeaponLevel);
			System.out.print("[Armor Control] Gold Weapon level is set to: " + plugin.goldWeaponLevel);
			System.out.print("[Armor Control] Iron Weapon level is set to: " + plugin.ironWeaponLevel);
			System.out.print("[Armor Control] Diamond Weapon level is set to: " + plugin.diamondWeaponLevel);
			System.out.print("[Armor Control] Bow level is set to: " + plugin.bowLevel);
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
			if (mode.equals("wood")) return plugin.woodToolLevel;
			else if (mode.equals("stone")) return plugin.stoneToolLevel;
			else if (mode.equals("gold")) return plugin.goldToolLevel;
			else if (mode.equals("iron")) return plugin.ironToolLevel;
			else if (mode.equals("diamond")) return plugin.diamondToolLevel;
			else if (mode.equals("bow")) return plugin.bowLevel;
		}
		else if (item.equals("tool")) {
			if (mode.equals("wood")) return plugin.woodToolLevel;
			else if (mode.equals("stone")) return plugin.stoneToolLevel;
			else if (mode.equals("gold")) return plugin.goldToolLevel;
			else if (mode.equals("iron")) return plugin.ironToolLevel;
			else if (mode.equals("diamond")) return plugin.diamondToolLevel;
		}
		else if (item.equals("armor")) {
			if (mode.equals("leather")) return plugin.leatherArmorLevel;
			else if (mode.equals("chain")) return plugin.chainArmorLevel;
			else if (mode.equals("gold")) return plugin.goldArmorLevel;
			else if (mode.equals("iron")) return plugin.ironArmorLevel;
			else if (mode.equals("diamond")) return plugin.diamondArmorLevel;
		}
		return 0;
	}
	
	public void checkInventoryforArmor(Player player) {
	    if (plugin.inv.getHelmet() != null) {
	    	for (int i=0;i<plugin.helmetIDs.length;i++) {
		    	if (plugin.helmetIDs[i] == plugin.inv.getHelmet().getType().getId()) {
		    		
		    		String mode;
		    		if (plugin.inv.getHelmet().getType().getId() == 298) {
		    			if (checkLevel(player, "leather", "armor")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.inv.getHelmet().getType().getId() == 302) {
		    			if (checkLevel(player, "chain", "armor")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.inv.getHelmet().getType().getId() == 306) {
		    			if (checkLevel(player, "iron", "armor")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.inv.getHelmet().getType().getId() == 310) {
		    			if (checkLevel(player, "diamond", "armor")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.inv.getHelmet().getType().getId() == 314) {
		    			if (checkLevel(player, "gold", "armor")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			System.out.print("No ID matched!");
		    			break;
		    		}
		    		plugin.armorPart = new ItemStack(plugin.inv.getHelmet().getType().getId());
		    		plugin.armorPart.setDurability(plugin.inv.getHelmet().getDurability());
		    		if (plugin.inv.getHelmet().getEnchantments().size() != 0) {
		    			plugin.armorPart.addEnchantments(plugin.inv.getHelmet().getEnchantments());
		    		}
		    		plugin.inv.addItem(plugin.armorPart);
		    		plugin.inv.setHelmet(plugin.air);
		    		player.sendMessage(ChatColor.RED + "You cannot wear this helmet. You must be at least level: " + findLevel(mode, "armor"));
		    		break;
		    	}
		    }
	    }
	    if (plugin.inv.getChestplate() != null) {
	    	for (int i=0;i<plugin.chestplateIDs.length;i++) {
		    	if (plugin.chestplateIDs[i] == plugin.inv.getChestplate().getType().getId()) {
		    		
		    		String mode;
		    		if (plugin.inv.getChestplate().getType().getId() == 299) {
		    			if (checkLevel(player, "leather", "armor")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.inv.getChestplate().getType().getId() == 303) {
		    			if (checkLevel(player, "chain", "armor")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.inv.getChestplate().getType().getId() == 307) {
		    			if (checkLevel(player, "iron", "armor")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.inv.getChestplate().getType().getId() == 311) {
		    			if (checkLevel(player, "diamond", "armor")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.inv.getChestplate().getType().getId() == 315) {
		    			if (checkLevel(player, "gold", "armor")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			break;
		    		}
		    		plugin.armorPart = new ItemStack(plugin.inv.getChestplate().getType().getId());
		    		plugin.armorPart.setDurability(plugin.inv.getChestplate().getDurability());
		    		if (plugin.inv.getChestplate().getEnchantments().size() != 0) {
		    			plugin.armorPart.addEnchantments(plugin.inv.getChestplate().getEnchantments());
		    		}
		    		plugin.inv.addItem(plugin.armorPart);
		    		plugin.inv.setChestplate(plugin.air);
		    		player.sendMessage(ChatColor.RED + "You cannot wear this chestplate. You must be at least level: " + findLevel(mode, "armor"));
		    		break;
		    		}
		    	}
		    }
	    if (plugin.inv.getLeggings() != null) {
	    	for (int i=0;i<plugin.leggingIDs.length;i++) {
		    	if (plugin.leggingIDs[i] == plugin.inv.getLeggings().getType().getId()) {
		    		
		    		String mode;
		    		if (plugin.inv.getLeggings().getType().getId() == 300) {
		    			if (checkLevel(player, "leather", "armor")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.inv.getLeggings().getType().getId() == 304) {
		    			if (checkLevel(player, "chain", "armor")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.inv.getLeggings().getType().getId() == 308) {
		    			if (checkLevel(player, "iron", "armor")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.inv.getLeggings().getType().getId() == 312) {
		    			if (checkLevel(player, "diamond", "armor")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.inv.getLeggings().getType().getId() == 316) {
		    			if (checkLevel(player, "gold", "armor")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			break;
		    		}
		    		plugin.armorPart = new ItemStack(plugin.inv.getLeggings().getType().getId());
		    		plugin.armorPart.setDurability(plugin.inv.getLeggings().getDurability());
		    		if (plugin.inv.getLeggings().getEnchantments().size() != 0) {
		    			plugin.armorPart.addEnchantments(plugin.inv.getLeggings().getEnchantments());
		    		}
		    		plugin.inv.addItem(plugin.armorPart);
		    		plugin.inv.setLeggings(plugin.air);
		    		player.sendMessage(ChatColor.RED + "You cannot wear these leggings. You must be at least level: " + findLevel(mode, "armor"));
		    		break;
		    	}
		    }
	    }
	    if (plugin.inv.getBoots() != null) {
	    	for (int i=0;i<plugin.bootIDs.length;i++) {
		    	if (plugin.bootIDs[i] == plugin.inv.getBoots().getType().getId()) {

		    		String mode;
		    		if (plugin.inv.getBoots().getType().getId() == 301) {
		    			if (checkLevel(player, "leather", "armor")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.inv.getBoots().getType().getId() == 305) {
		    			if (checkLevel(player, "chain", "armor")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.inv.getBoots().getType().getId() == 309) {
		    			if (checkLevel(player, "iron", "armor")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.inv.getBoots().getType().getId() == 313) {
		    			if (checkLevel(player, "diamond", "armor")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.inv.getBoots().getType().getId() == 317) {
		    			if (checkLevel(player, "gold", "armor")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			break;
		    		}
		    		plugin.armorPart = new ItemStack(plugin.inv.getBoots().getType().getId());
		    		plugin.armorPart.setDurability(plugin.inv.getBoots().getDurability());
		    		if (plugin.inv.getBoots().getEnchantments().size() != 0) {
		    			plugin.armorPart.addEnchantments(plugin.inv.getBoots().getEnchantments());
		    		}
		    		plugin.inv.addItem(plugin.armorPart);
		    		plugin.inv.setBoots(plugin.air);
		    		player.sendMessage(ChatColor.RED + "You cannot wear these boots. You must be at least level: " + findLevel(mode, "armor"));
		    		break;
		    	}
		    }
		}
	}
}
