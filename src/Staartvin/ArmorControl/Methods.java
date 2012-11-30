package Staartvin.ArmorControl;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Methods {
	
	ArmorControl plugin;
	
	public Methods (ArmorControl plugin) {
		this.plugin = plugin;
	}
	
	public void checkLimits() {
		try {
			plugin.leatherLevel = plugin.getConfig().getInt("leatherLevel");
			plugin.chainLevel = plugin.getConfig().getInt("chainLevel");
			plugin.goldLevel = plugin.getConfig().getInt("goldLevel");
			plugin.ironLevel = plugin.getConfig().getInt("ironLevel");
			plugin.diamondLevel = plugin.getConfig().getInt("diamondLevel");
			
			if (plugin.getConfig().getBoolean("verboseLogging")) {
			System.out.print("[Armor Control] Leather level is set to: " + plugin.leatherLevel);
			System.out.print("[Armor Control] Chain level is set to: " + plugin.chainLevel);
			System.out.print("[Armor Control] Gold level is set to: " + plugin.goldLevel);
			System.out.print("[Armor Control] Iron level is set to: " + plugin.ironLevel);
			System.out.print("[Armor Control] Diamond level is set to: " + plugin.diamondLevel);
			}	
		} catch (Exception e) {
			System.out.print("[Armor Control] Could not parse levels in config to integers!");
			System.out.print("[Armor Control] Switching to default levels!");
		}
	}
	
	public int findLevel(String mode) {
		if (mode.equals("leather")) {
			return plugin.leatherLevel;
		} else if (mode.equals("chain")) {
			return plugin.chainLevel;
		} else if (mode.equals("gold")) {
			return plugin.goldLevel;
		} else if (mode.equals("iron")) {
			return plugin.ironLevel;
		} else if (mode.equals("diamond")) {
			return plugin.diamondLevel;
		} else {
			return 0;
		}
	}
	
	public boolean checkLevel(Player player, String mode) {
	
		if (mode.equals("leather")) {
			if (player.getLevel() >= plugin.leatherLevel) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (mode.equals("chain")) {
			if (player.getLevel() >= plugin.chainLevel) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (mode.equals("gold")) {
			if (player.getLevel() >= plugin.goldLevel) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (mode.equals("iron")) {
			if (player.getLevel() >= plugin.ironLevel) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (mode.equals("diamond")) {
			if (player.getLevel() >= plugin.diamondLevel) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public void checkInventory(Player player) {
	    if (plugin.inv.getHelmet() != null) {
	    	for (int i=0;i<plugin.helmetIDs.length;i++) {
		    	if (plugin.helmetIDs[i] == plugin.inv.getHelmet().getType().getId()) {
		    		
		    		String mode;
		    		if (plugin.inv.getHelmet().getType().getId() == 298) {
		    			if (checkLevel(player, "leather")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.inv.getHelmet().getType().getId() == 302) {
		    			if (checkLevel(player, "chain")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.inv.getHelmet().getType().getId() == 306) {
		    			if (checkLevel(player, "iron")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.inv.getHelmet().getType().getId() == 310) {
		    			if (checkLevel(player, "diamond")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.inv.getHelmet().getType().getId() == 314) {
		    			if (checkLevel(player, "gold")) {
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
		    		player.sendMessage(ChatColor.RED + "You cannot wear this helmet. You must be at least level: " + findLevel(mode));
		    		break;
		    	}
		    }
	    }
	    if (plugin.inv.getChestplate() != null) {
	    	for (int i=0;i<plugin.chestplateIDs.length;i++) {
		    	if (plugin.chestplateIDs[i] == plugin.inv.getChestplate().getType().getId()) {
		    		
		    		String mode;
		    		if (plugin.inv.getChestplate().getType().getId() == 299) {
		    			if (checkLevel(player, "leather")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.inv.getChestplate().getType().getId() == 303) {
		    			if (checkLevel(player, "chain")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.inv.getChestplate().getType().getId() == 307) {
		    			if (checkLevel(player, "iron")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.inv.getChestplate().getType().getId() == 311) {
		    			if (checkLevel(player, "diamond")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.inv.getChestplate().getType().getId() == 315) {
		    			if (checkLevel(player, "gold")) {
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
		    		player.sendMessage(ChatColor.RED + "You cannot wear this chestplate. You must be at least level: " + findLevel(mode));
		    		break;
		    		}
		    	}
		    }
	    if (plugin.inv.getLeggings() != null) {
	    	for (int i=0;i<plugin.leggingIDs.length;i++) {
		    	if (plugin.leggingIDs[i] == plugin.inv.getLeggings().getType().getId()) {
		    		
		    		String mode;
		    		if (plugin.inv.getLeggings().getType().getId() == 300) {
		    			if (checkLevel(player, "leather")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.inv.getLeggings().getType().getId() == 304) {
		    			if (checkLevel(player, "chain")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.inv.getLeggings().getType().getId() == 308) {
		    			if (checkLevel(player, "iron")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.inv.getLeggings().getType().getId() == 312) {
		    			if (checkLevel(player, "diamond")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.inv.getLeggings().getType().getId() == 316) {
		    			if (checkLevel(player, "gold")) {
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
		    		player.sendMessage(ChatColor.RED + "You cannot wear these leggings. You must be at least level: " + findLevel(mode));
		    		break;
		    	}
		    }
	    }
	    if (plugin.inv.getBoots() != null) {
	    	for (int i=0;i<plugin.bootIDs.length;i++) {
		    	if (plugin.bootIDs[i] == plugin.inv.getBoots().getType().getId()) {

		    		String mode;
		    		if (plugin.inv.getBoots().getType().getId() == 301) {
		    			if (checkLevel(player, "leather")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (plugin.inv.getBoots().getType().getId() == 305) {
		    			if (checkLevel(player, "chain")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (plugin.inv.getBoots().getType().getId() == 309) {
		    			if (checkLevel(player, "iron")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (plugin.inv.getBoots().getType().getId() == 313) {
		    			if (checkLevel(player, "diamond")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (plugin.inv.getBoots().getType().getId() == 317) {
		    			if (checkLevel(player, "gold")) {
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
		    		player.sendMessage(ChatColor.RED + "You cannot wear these boots. You must be at least level: " + findLevel(mode));
		    		break;
		    	}
		    }
	    }	
	}
}
