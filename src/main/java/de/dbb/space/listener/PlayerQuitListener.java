package de.dbb.space.listener;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.dbb.space.main.Main;
import de.dbb.space.misc.Color;
import de.dbb.space.misc.FileHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class PlayerQuitListener implements Listener {

	private Main main = Main.getInstance();
	private FileHandler fileHandler = main.getFileHandler();
	
	private YamlConfiguration playerDataCfg;
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		playerDataCfg = YamlConfiguration.loadConfiguration(fileHandler.playerDataFile);
		
		String username = event.getPlayer().getName();
		String uniqueId = event.getPlayer().getUniqueId().toString();
		
		playerDataCfg.set(uniqueId + ".Personal.Username", username.toLowerCase());
		playerDataCfg.set(uniqueId + ".Personal.UniqueId", uniqueId);
		try {
			playerDataCfg.save(fileHandler.playerDataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		event.quitMessage(Component.text("Spieler ").color(Color.text)
				  .append(Component.text(username).color(Color.player)
				  .append(Component.text(" hat den Server ").color(Color.text)
				  .append(Component.text("verlassen").color(NamedTextColor.RED)))));
	}

}
