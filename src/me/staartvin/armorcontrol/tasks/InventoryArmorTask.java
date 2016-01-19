package me.staartvin.armorcontrol.tasks;

import me.staartvin.armorcontrol.ArmorControl;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryArmorTask extends BukkitRunnable {

	private ArmorControl plugin;
	
	public InventoryArmorTask(ArmorControl instance) {
		plugin = instance;
	}
	
	@Override
	public void run() {	
		for (Player player: plugin.getServer().getOnlinePlayers()) {

			// Player has got exempt permission
			if (player.hasPermission("armorcontrol.exempt"))
				return;

			plugin.getResManager().checkArmor(player);
			
		}	
	}

}
