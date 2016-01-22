package me.staartvin.armorcontrol.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.staartvin.armorcontrol.ArmorControl;
import me.staartvin.armorcontrol.config.ConfigHandler.message;
import me.staartvin.armorcontrol.restrictions.RestrictionsManager.actionType;
import me.staartvin.armorcontrol.util.Vector3D;

public class PlayerInteractListener implements Listener {

	ArmorControl plugin;

	public PlayerInteractListener(ArmorControl plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		// We do not have to check on this world.
		if (plugin.getAPI().isDisabledWorld(player.getLocation().getWorld().getName()))
			return;

		ItemStack item = event.getItem();

		// No item in hand, so nothing to check
		if (item == null)
			return;

		int requiredLevel = 0;
		System.out.println("------------------------");

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
						System.out.println("Looked at player '" + target.getName() + "'!");
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

		requiredLevel = plugin.getResManager().getRequiredLevel(item, type);

		System.out.println("Required level: " + requiredLevel);

		// Required level is 0.
		if (requiredLevel <= 0)
			return;

		// Player does not have the sufficient level
		if (requiredLevel > player.getLevel()) {
			plugin.getMessageHandler().sendMessage(player, plugin.getConfigHandler()
					.getMessage(message.NOT_ALLOWED_TO_USE, type.toString(), requiredLevel + ""));

			event.setCancelled(true);

			System.out.println("Event is cancelled.");
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
