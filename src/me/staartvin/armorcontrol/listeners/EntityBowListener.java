package me.staartvin.armorcontrol.listeners;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.message;
import me.staartvin.armorcontrol.requirements.Requirement;
import me.staartvin.armorcontrol.restrictions.Restriction;
import me.staartvin.armorcontrol.restrictions.RestrictionsManager.actionType;
import net.md_5.bungee.api.ChatColor;

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
		
		// Check if restrictions are disabled on this world.
		if (plugin.getAPI().isDisabledWorld(bow, worldName)) {
			return;
		}

		Restriction r = plugin.getResManager().getRestriction(bow);

		// No restriction for this item found.
		if (r == null)
			return;

		List<Requirement> failed = r.getFailedRequirements(player, action);

		// All requirements are met, allow to use.
		if (failed.isEmpty())
			return;

		// Cannot use this item
		event.setCancelled(true);

		plugin.getMessageHandler().sendMessage(player,
				plugin.getConfigHandler().getMessage(message.NOT_ALLOWED_TO_SHOOT_BOW, action.toString()));

		// Show what the player still has to do to use this item.
		for (Requirement failedReq : failed) {
			player.sendMessage(ChatColor.RED + "- " + failedReq.getDescription());
		}
	}
}
