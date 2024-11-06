package de.dbb.space.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class ProjectileHitListener implements Listener {
	
	@EventHandler
	public void onHit(ProjectileHitEvent event) {
		if(event.getEntity() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getEntity();
		
			if(!(arrow.getShooter() instanceof Player)) return;
			Player shooter = (Player) arrow.getShooter();
						
			if(arrow.getWeapon().getItemMeta().hasDisplayName() == false) return;
			if(!arrow.getWeapon().getItemMeta().displayName().equals(Component.text("Explosive Bow").color(NamedTextColor.RED))) return;

			if(event.getHitEntity() instanceof Player) {
				Player hitPlayer = (Player) event.getHitEntity();
							
				hitPlayer.damage(3);
				hitPlayer.setFireTicks(60);
				hitPlayer.teleport(hitPlayer.getLocation().add(0, 5, 0));
				hitPlayer.setVelocity(new Vector(0, 0.1, 0));
				
				hitPlayer.playSound(hitPlayer.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.3f, 0.3f);
				shooter.playSound(shooter.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.3f, 0.3f);
			} else {
				arrow.remove();
			}
		}
	}
	
}
