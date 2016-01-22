package me.staartvin.armorcontrol.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.message;
import me.staartvin.armorcontrol.restrictions.RestrictionsManager.actionType;

public class EntityBowListener implements Listener {

	ArmorControl plugin;

	public EntityBowListener(ArmorControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onBowUse(EntityShootBowEvent event) {
		// Entity is not a player
		if (!(event.getEntity() instanceof Player))
			return;

		Player player = (Player) event.getEntity();
		
		String worldName = player.getLocation().getWorld().getName();

		// We do not have to check on this world.
		if (plugin.getAPI().isDisabledWorld(worldName))
			return;

		// Ignore creative?
		if (plugin.getConfigHandler().shouldIgnoreCreative()) {
			if (player.getGameMode().equals(GameMode.CREATIVE))
				return;
		}

		ItemStack bow = event.getBow();

		actionType action = actionType.SHOOT_BOW;

		int requiredLevel = plugin.getResManager().getRequiredLevel(bow, action);
		
		// Check if restrictions are disabled on this world.
		if (plugin.getAPI().isDisabledWorld(bow, worldName)) {
			return;
		}

		// Player does not have the sufficient level
		if (requiredLevel > player.getLevel()) {
			plugin.getMessageHandler().sendMessage(player,
					plugin.getConfigHandler().getMessage(message.NOT_ALLOWED_TO_SHOOT_BOW, requiredLevel + ""));

			event.setCancelled(true);
		}
	}
}
