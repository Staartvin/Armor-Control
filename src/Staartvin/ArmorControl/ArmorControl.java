package Staartvin.ArmorControl;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class ArmorControl extends JavaPlugin implements Listener {

	int[] helmetIDs = {298, 302, 306, 310, 314};
	int[] chestplateIDs = {299, 303, 307, 311, 315};
	int[] leggingIDs = {300, 304, 308, 312, 316};
	int[] bootIDs = {301, 305, 309, 313, 317};
	ItemStack armorPart;
	ItemStack air = new ItemStack(Material.AIR, 1);
	PlayerInventory inv;
	
	int leatherLevel = 5;
	int chainLevel = 11;
	int goldLevel = 17;
	int ironLevel = 22;
	int diamondLevel = 30;
	

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		loadConfiguration();
		checkLimits();
		System.out.println("[" + getDescription().getName()
				+ "] has been enabled!");
	}

	public void onDisable() {
		reloadConfig();
		saveConfig();
		System.out.println("[" + getDescription().getName()
				+ "] has been disabled!");
	}

	public void loadConfiguration() {
		getConfig().options().header(
				"ArmorControl v" + getDescription().getVersion() + " Config");
		
		getConfig().addDefault("verboseLogging", true);
		getConfig().addDefault("leatherLevel", 5);
		getConfig().addDefault("chainLevel", 11);
		getConfig().addDefault("goldLevel", 17);
		getConfig().addDefault("ironLevel", 22);
		getConfig().addDefault("diamondLevel", 30);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent event) {
		if (getServer().getPlayer(event.getPlayer().getName()) == null) {
	    	return;
	    }
	    Player player = getServer().getPlayer(event.getPlayer().getName());
	    if (player.hasPermission("armorcontrol.exempt")) {
	    	return;
	    }
	    inv = getServer().getPlayer(event.getPlayer().getName()).getInventory();
	    checkInventory(player); 
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if (getServer().getPlayer(event.getPlayer().getName()) == null) {
	    	return;
	    }
	    Player player = getServer().getPlayer(event.getPlayer().getName());
	    if (player.hasPermission("armorcontrol.exempt")) {
	    	return;
	    }
	    inv = getServer().getPlayer(event.getPlayer().getName()).getInventory();
	    checkInventory(player); 
	}
	
	public void checkLimits() {
		try {
			leatherLevel = getConfig().getInt("leatherLevel");
			chainLevel = getConfig().getInt("chainLevel");
			goldLevel = getConfig().getInt("goldLevel");
			ironLevel = getConfig().getInt("ironLevel");
			diamondLevel = getConfig().getInt("diamondLevel");
			
			if (getConfig().getBoolean("verboseLogging")) {
			System.out.print("[Armor Control] Leather level is set to: " + leatherLevel);
			System.out.print("[Armor Control] Chain level is set to: " + chainLevel);
			System.out.print("[Armor Control] Gold level is set to: " + goldLevel);
			System.out.print("[Armor Control] Iron level is set to: " + ironLevel);
			System.out.print("[Armor Control] Diamond level is set to: " + diamondLevel);
			}	
		} catch (Exception e) {
			System.out.print("[Armor Control] Could not parse levels in config to integers!");
			System.out.print("[Armor Control] Switching to default levels!");
		}
	}
	
	public int findLevel(String mode) {
		if (mode.equals("leather")) {
			return leatherLevel;
		} else if (mode.equals("chain")) {
			return chainLevel;
		} else if (mode.equals("gold")) {
			return goldLevel;
		} else if (mode.equals("iron")) {
			return ironLevel;
		} else if (mode.equals("diamond")) {
			return diamondLevel;
		} else {
			return 0;
		}
	}
	
	public boolean checkLevel(Player player, String mode) {
	
		if (mode.equals("leather")) {
			if (player.getLevel() >= leatherLevel) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (mode.equals("chain")) {
			if (player.getLevel() >= chainLevel) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (mode.equals("gold")) {
			if (player.getLevel() >= goldLevel) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (mode.equals("iron")) {
			if (player.getLevel() >= ironLevel) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (mode.equals("diamond")) {
			if (player.getLevel() >= diamondLevel) {
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
	    if (inv.getHelmet() != null) {
	    	for (int i=0;i<helmetIDs.length;i++) {
		    	if (helmetIDs[i] == inv.getHelmet().getType().getId()) {
		    		
		    		String mode;
		    		if (inv.getHelmet().getType().getId() == 298) {
		    			if (checkLevel(player, "leather")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (inv.getHelmet().getType().getId() == 302) {
		    			if (checkLevel(player, "chain")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (inv.getHelmet().getType().getId() == 306) {
		    			if (checkLevel(player, "iron")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (inv.getHelmet().getType().getId() == 310) {
		    			if (checkLevel(player, "diamond")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (inv.getHelmet().getType().getId() == 314) {
		    			if (checkLevel(player, "gold")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			System.out.print("No ID matched!");
		    			break;
		    		}
		    		armorPart = new ItemStack(inv.getHelmet().getType().getId());
		    		armorPart.setDurability(inv.getHelmet().getDurability());
		    		if (inv.getHelmet().getEnchantments().size() != 0) {
		    			armorPart.addEnchantments(inv.getHelmet().getEnchantments());
		    		}
		    		inv.addItem(armorPart);
		    		inv.setHelmet(air);
		    		player.sendMessage(ChatColor.RED + "You cannot wear this helmet. You must be at least level: " + findLevel(mode));
		    		break;
		    	}
		    }
	    }
	    if (inv.getChestplate() != null) {
	    	for (int i=0;i<chestplateIDs.length;i++) {
		    	if (chestplateIDs[i] == inv.getChestplate().getType().getId()) {
		    		
		    		String mode;
		    		if (inv.getChestplate().getType().getId() == 299) {
		    			if (checkLevel(player, "leather")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (inv.getChestplate().getType().getId() == 303) {
		    			if (checkLevel(player, "chain")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (inv.getChestplate().getType().getId() == 307) {
		    			if (checkLevel(player, "iron")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (inv.getChestplate().getType().getId() == 311) {
		    			if (checkLevel(player, "diamond")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (inv.getChestplate().getType().getId() == 315) {
		    			if (checkLevel(player, "gold")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			break;
		    		}
		    		armorPart = new ItemStack(inv.getChestplate().getType().getId());
		    		armorPart.setDurability(inv.getChestplate().getDurability());
		    		if (inv.getChestplate().getEnchantments().size() != 0) {
		    			armorPart.addEnchantments(inv.getChestplate().getEnchantments());
		    		}
		    		inv.addItem(armorPart);
		    		inv.setChestplate(air);
		    		player.sendMessage(ChatColor.RED + "You cannot wear this chestplate. You must be at least level: " + findLevel(mode));
		    		break;
		    		}
		    	}
		    }
	    if (inv.getLeggings() != null) {
	    	for (int i=0;i<leggingIDs.length;i++) {
		    	if (leggingIDs[i] == inv.getLeggings().getType().getId()) {
		    		
		    		String mode;
		    		if (inv.getLeggings().getType().getId() == 300) {
		    			if (checkLevel(player, "leather")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (inv.getLeggings().getType().getId() == 304) {
		    			if (checkLevel(player, "chain")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (inv.getLeggings().getType().getId() == 308) {
		    			if (checkLevel(player, "iron")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (inv.getLeggings().getType().getId() == 312) {
		    			if (checkLevel(player, "diamond")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (inv.getLeggings().getType().getId() == 316) {
		    			if (checkLevel(player, "gold")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			break;
		    		}
		    		armorPart = new ItemStack(inv.getLeggings().getType().getId());
		    		armorPart.setDurability(inv.getLeggings().getDurability());
		    		if (inv.getLeggings().getEnchantments().size() != 0) {
		    			armorPart.addEnchantments(inv.getLeggings().getEnchantments());
		    		}
		    		inv.addItem(armorPart);
		    		inv.setLeggings(air);
		    		player.sendMessage(ChatColor.RED + "You cannot wear these leggings. You must be at least level: " + findLevel(mode));
		    		break;
		    	}
		    }
	    }
	    if (inv.getBoots() != null) {
	    	for (int i=0;i<bootIDs.length;i++) {
		    	if (bootIDs[i] == inv.getBoots().getType().getId()) {

		    		String mode;
		    		if (inv.getBoots().getType().getId() == 301) {
		    			if (checkLevel(player, "leather")) {
		    				break;
		    			}
		    			mode = "leather";
		    		} else if (inv.getBoots().getType().getId() == 305) {
		    			if (checkLevel(player, "chain")) {
		    				break;
		    			}
		    			mode = "chain";
		    		} else if (inv.getBoots().getType().getId() == 309) {
		    			if (checkLevel(player, "iron")) {
		    				break;
		    			}
		    			mode = "iron";
		    		} else if (inv.getBoots().getType().getId() == 313) {
		    			if (checkLevel(player, "diamond")) {
		    				break;
		    			}
		    			mode = "diamond";
		    		} else if (inv.getBoots().getType().getId() == 317) {
		    			if (checkLevel(player, "gold")) {
		    				break;
		    			}
		    			mode = "gold";
		    		} else {
		    			break;
		    		}
		    		armorPart = new ItemStack(inv.getBoots().getType().getId());
		    		armorPart.setDurability(inv.getBoots().getDurability());
		    		if (inv.getBoots().getEnchantments().size() != 0) {
		    			armorPart.addEnchantments(inv.getBoots().getEnchantments());
		    		}
		    		inv.addItem(armorPart);
		    		inv.setBoots(air);
		    		player.sendMessage(ChatColor.RED + "You cannot wear these boots. You must be at least level: " + findLevel(mode));
		    		break;
		    	}
		    }
	    }	
	}
}
