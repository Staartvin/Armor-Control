package me.staartvin.armorcontrol.listeners;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.message;
import me.staartvin.armorcontrol.requirements.Requirement;
import me.staartvin.armorcontrol.restrictions.Restriction;
import me.staartvin.armorcontrol.restrictions.RestrictionsManager.actionType;
import me.staartvin.armorcontrol.util.Vector3D;
import net.md_5.bungee.api.ChatColor;

public class PlayerInteractListener implements Listener {

	ArmorControl plugin;

	public PlayerInteractListener(ArmorControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

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

		ItemStack item = event.getItem();

		// No item in hand, so nothing to check
		if (item == null)
			return;

		actionType type = null;

		// Check if player is not actually clicking on a player
		if (event.getAction().equals(Action.LEFT_CLICK_AIR)) {

			Location observerPos = player.getEyeLocation();
			Vector3D observerDir = new Vector3D(observerPos.getDirection());

			Vector3D observerStart = new Vector3D(observerPos);
			Vector3D observerEnd = observerStart.add(observerDir.multiply(3));

			Player hit = null;

			// Get nearby entities
			for (Player target : player.getWorld().getPlayers()) {
				// Bounding box of the given player
				Vector3D targetPos = new Vector3D(target.getLocation());
				Vector3D minimum = targetPos.add(-0.5, 0, -0.5);
				Vector3D maximum = targetPos.add(0.5, 1.67, 0.5);

				if (target != player && hasIntersection(observerStart, observerEnd, minimum, maximum)) {
					if (hit == null || hit.getLocation().distanceSquared(observerPos) > target.getLocation()
							.distanceSquared(observerPos)) {

						hit = target;
					}
				}
			}

			// Player clicked on a player!
			if (hit != null) {
				type = actionType.LEFT_CLICK_PLAYER;
			}
		}

		String blockAction = event.getAction().toString();

		if (type == null) {
			for (actionType actionType : actionType.values()) {

				if (blockAction.equalsIgnoreCase(actionType.toString())) {
					type = actionType;
					break;
				}
			}
		}

		// Check if restrictions are disabled on this world.
		if (plugin.getAPI().isDisabledWorld(item, worldName)) {
			return;
		}

		Restriction r = plugin.getResManager().getRestriction(item);

		// No restriction for this item found.
		if (r == null)
			return;

		List<Requirement> failed = r.getFailedRequirements(player, type);

		// All requirements are met, allow to use.
		if (failed.isEmpty())
			return;

		// Cannot use this item
		event.setCancelled(true);

		plugin.getMessageHandler().sendMessage(player,
				plugin.getConfigHandler().getMessage(message.NOT_ALLOWED_TO_USE, type.toString()));

		// Show what the player still has to do to use this item.
		for (Requirement failedReq : failed) {
			player.sendMessage(ChatColor.RED + "- " + failedReq.getDescription());
		}
	}

	// Source:
	// http://www.gamedev.net/topic/338987-aabb---line-segment-intersection-test/
	private boolean hasIntersection(Vector3D p1, Vector3D p2, Vector3D min, Vector3D max) {
		final double epsilon = 0.0001f;

		Vector3D d = p2.subtract(p1).multiply(0.5);
		Vector3D e = max.subtract(min).multiply(0.5);
		Vector3D c = p1.add(d).subtract(min.add(max).multiply(0.5));
		Vector3D ad = d.abs();

		if (Math.abs(c.x) > e.x + ad.x)
			return false;
		if (Math.abs(c.y) > e.y + ad.y)
			return false;
		if (Math.abs(c.z) > e.z + ad.z)
			return false;

		if (Math.abs(d.y * c.z - d.z * c.y) > e.y * ad.z + e.z * ad.y + epsilon)
			return false;
		if (Math.abs(d.z * c.x - d.x * c.z) > e.z * ad.x + e.x * ad.z + epsilon)
			return false;
		if (Math.abs(d.x * c.y - d.y * c.x) > e.x * ad.y + e.y * ad.x + epsilon)
			return false;

		return true;
	}

}
