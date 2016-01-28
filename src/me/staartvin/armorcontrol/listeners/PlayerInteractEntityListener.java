package me.staartvin.armorcontrol.listeners;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.message;
import me.staartvin.armorcontrol.requirements.Requirement;
import me.staartvin.armorcontrol.restrictions.Restriction;
import me.staartvin.armorcontrol.restrictions.RestrictionsManager.actionType;
import net.md_5.bungee.api.ChatColor;

public class PlayerInteractEntityListener implements Listener {

	ArmorControl plugin;

	public PlayerInteractEntityListener(ArmorControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {

		Player player = event.getPlayer();
		
		String worldName = player.getLocation().getWorld().getName();

		// We do not have to check on this world.
		if (plugin.getAPI().isDisabledWorld(worldName))
			return;

		// Ignore creative?
		if (plugin.getConfigHandler().shouldIgnoreCreative()) {
			if (player.getGameMode().equals(GameMode.CREATIVE))
				return;
		}

		actionType action = actionType.RIGHT_CLICK_MOB;

		if (event.getRightClicked().getType().equals(EntityType.PLAYER)) {
			// Action is right clicked player
			action = actionType.RIGHT_CLICK_PLAYER;
		}

		ItemStack item = player.getItemInHand();

		// No item in hand, so nothing to check
		if (item == null)
			return;

		String blockAction = action.toString();

		// Check if restrictions are disabled on this world.
		if (plugin.getAPI().isDisabledWorld(item, worldName)) {
			return;
		}

		Restriction r = plugin.getResManager().getRestriction(item);

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
				plugin.getConfigHandler().getMessage(message.NOT_ALLOWED_TO_USE, blockAction));

		// Show what the player still has to do to use this item.
		for (Requirement failedReq : failed) {
			player.sendMessage(ChatColor.RED + "- " + failedReq.getDescription());
		}
	}
}
