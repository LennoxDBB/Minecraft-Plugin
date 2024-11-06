package de.dbb.space.commands;

import java.text.DecimalFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import de.dbb.space.main.Main;
import de.dbb.space.misc.Color;
import de.dbb.space.misc.FileHandler;
import de.dbb.space.misc.User;
import de.dbb.space.misc.Variables;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class GemsCommand implements CommandExecutor {
	
	Main main = Main.getInstance();
	FileHandler fileHandler = main.getFileHandler();
	Variables var = main.getVariables();
	User user = main.getUser();
	
	DecimalFormat format = new DecimalFormat(",###");
	YamlConfiguration cfg;
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		
		cfg = YamlConfiguration.loadConfiguration(fileHandler.playerDataFile);
		
		if(command.getName().equalsIgnoreCase("gems")) {
			
			if(!(sender instanceof Player)) {
				sender.sendMessage(var.noPlayer);
				return true;
			}
			
			Player player = (Player) sender;
			
			double gems;
			
			if(args.length == 0) {
				gems = cfg.getDouble(player.getUniqueId().toString() + ".Economy.Gems");
				
				player.sendMessage(var.prefix.append(Component.text("Du besitzt ").color(Color.text))
											 .append(Component.text(format.format(gems) + " Edelsteine").color(Color.gems).decorate(TextDecoration.BOLD)));
				return true;
			} else if(args.length == 1) {
				String playerName = args[0];
				String uniqueId = user.getUniqueIdByName(playerName);
				
				if(uniqueId == null) {
					player.sendMessage(var.playerNotExist);
					return true;
				}
				
				if(user.areGemsPrivate(uniqueId)) {
					player.sendMessage(var.prefix
							   .append(Component.text("Der Spieler ").color(Color.text)
							   .append(Component.text(playerName).color(Color.player)
							   .append(Component.text(" hält seine Edelsteine ").color(Color.text)
							   .append(Component.text("privat").color(NamedTextColor.RED))))));
					return true;
				}
				
				gems = cfg.getDouble(uniqueId + ".Economy.Gems");
				
				player.sendMessage(var.prefix
						   .append(Component.text("Der Spieler ").color(Color.text)
						   .append(Component.text(playerName).color(Color.player)
						   .append(Component.text(" besitzt ").color(Color.text)
						   .append(Component.text(format.format(gems) + " Edelsteine").color(Color.gems).decorate(TextDecoration.BOLD))))));
				return true;
			} else {
				player.sendMessage(var.prefix.append(Component.text("Verwendung: /gems <player>").color(NamedTextColor.RED)));
				return true;
			}
			
		}
		
		return true;
	}
	
	
	
}
