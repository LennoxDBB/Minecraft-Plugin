package de.dbb.space.listener;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.dbb.space.main.Main;
import de.dbb.space.misc.Color;
import de.dbb.space.misc.FileHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class PlayerJoinListener implements Listener {

	private Main main = Main.getInstance();
	private FileHandler fileHandler = main.getFileHandler();
	
	private YamlConfiguration playerDataCfg;
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		playerDataCfg = YamlConfiguration.loadConfiguration(fileHandler.playerDataFile);
		
		String username = event.getPlayer().getName();
		String uniqueId = event.getPlayer().getUniqueId().toString();
		
		if(playerDataCfg.get(uniqueId + ".Personal.Username") == null) {
			playerDataCfg.set(uniqueId + ".Economy.Gems", 0);
			playerDataCfg.set(uniqueId + ".Economy.Privacy.Gems", true);
		}
		
		playerDataCfg.set(uniqueId + ".Personal.Username", username.toLowerCase());
		playerDataCfg.set(uniqueId + ".Personal.UniqueId", uniqueId);
		try {
			playerDataCfg.save(fileHandler.playerDataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		event.joinMessage(Component.text("Spieler ").color(Color.text)
				  .append(Component.text(username).color(Color.player)
				  .append(Component.text(" hat den Server ").color(Color.text)
				  .append(Component.text("betreten").color(NamedTextColor.GREEN)))));
	}
	
}
