package me.staartvin.armorcontrol.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.message;
import me.staartvin.armorcontrol.restrictions.RestrictionsManager.actionType;

public class EntityDamageEntityListener implements Listener {

	ArmorControl plugin;

	public EntityDamageEntityListener(ArmorControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteract(EntityDamageByEntityEvent event) {

		Entity attacker = event.getDamager();
		
		// Attacker is not a player.
		if (!attacker.getType().equals(EntityType.PLAYER)) return;

		// We do not have to check on this world.
		if (plugin.getAPI().isDisabledWorld(attacker.getLocation().getWorld().getName()))
			return;

		Player player = (Player) attacker;
		
		System.out.println(player.getName() + " attacked " + event.getEntity());

		actionType action = actionType.LEFT_CLICK_MOB;

		if (event.getEntity().getType().equals(EntityType.PLAYER)) {
			// Action is left clicked player
			action = actionType.LEFT_CLICK_PLAYER;
		}

		ItemStack item = player.getItemInHand();

		// No item in hand, so nothing to check
		if (item == null)
			return;

		String blockAction = action.toString();

		int requiredLevel = 0;
		System.out.println("------------------------");

		requiredLevel = plugin.getResManager().getRequiredLevel(item, action);

		System.out.println("Required level: " + requiredLevel);

		// Required level is 0.
		if (requiredLevel <= 0)
			return;

		// Player does not have the sufficient level
		if (requiredLevel > player.getLevel()) {
			plugin.getMessageHandler().sendMessage(player,
					plugin.getConfigHandler().getMessage(message.NOT_ALLOWED_TO_USE, blockAction, requiredLevel + ""));

			event.setCancelled(true);

			System.out.println("Event (mob attack) is cancelled.");
		}
	}
}
