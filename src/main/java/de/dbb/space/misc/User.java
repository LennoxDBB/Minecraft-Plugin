package de.dbb.space.misc;

import org.bukkit.configuration.file.YamlConfiguration;

import de.dbb.space.main.Main;

public class User {

	private Main main = Main.getInstance();
	private FileHandler fileHandler = main.getFileHandler();
	private YamlConfiguration playerDataCfg;
	
	public String getUniqueIdByName(String playerName) {
		playerDataCfg = YamlConfiguration.loadConfiguration(fileHandler.playerDataFile);
		
		for(String uniqueId : playerDataCfg.getConfigurationSection("").getKeys(false)) {
			String username = playerDataCfg.getString(uniqueId + ".Personal.Username");
			
			if(username.equals(playerName.toLowerCase())) {
				return uniqueId;
			}
		}
		
		return null;
	}
	
	public boolean areGemsPrivate(String uniqueId) {
		playerDataCfg = YamlConfiguration.loadConfiguration(fileHandler.playerDataFile);
		
		return !playerDataCfg.getBoolean(uniqueId + ".Economy.Privacy.Gems");
	}
	
}
