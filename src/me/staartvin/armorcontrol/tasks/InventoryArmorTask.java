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
		if (!plugin.getConfig().getBoolean("UseArmorControl"))
			return;
		
		for (Player player: plugin.getServer().getOnlinePlayers()) {

			// Is this world disabled
			if (plugin.getWorldHandler().isDisabled(player.getWorld().getName()))
				return;
			// Player has got exempt permission
			if (player.hasPermission("armorcontrol.exempt"))
				return;

			plugin.getMethods().checkInventoryforArmor(player);
			
		}	
	}

}
