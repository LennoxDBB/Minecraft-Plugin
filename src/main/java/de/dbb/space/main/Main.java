package de.dbb.space.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.dbb.space.commands.GemsCommand;
import de.dbb.space.listener.PlayerJoinListener;
import de.dbb.space.listener.PlayerQuitListener;
import de.dbb.space.misc.FileHandler;
import de.dbb.space.misc.User;
import de.dbb.space.misc.Variables;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Main extends JavaPlugin {

	private static Main instance;
	
	private Variables var;
	private FileHandler fileHandler;
	private User user;
	
	public void onLoad() {
		instance = this;
		
		init();
	}
	
	public void onEnable() {
		registerItems();
		registerCommands();
		registerListener();
		
		Bukkit.getConsoleSender().sendMessage(var.prefix.append(Component.text("Das Plugin wurde gestartet!").color(NamedTextColor.GREEN)));
	}
	
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(var.prefix.append(Component.text("Das Plugin wurde gestoppt!").color(NamedTextColor.RED)));
	}
	
	
	
	private void init() {
		var = new Variables();
		fileHandler = new FileHandler();
		user = new User();
	}
	
	private void registerCommands() {
		this.getCommand("gems").setExecutor(new GemsCommand());
	}
	
	private void registerListener() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new PlayerJoinListener(), this);
		pluginManager.registerEvents(new PlayerQuitListener(), this);
	}
	
	private void registerItems() {
	}
	
	public static Main getInstance() { return instance; }
	
	public Variables getVariables() { return var; }
	public FileHandler getFileHandler() { return fileHandler; }
	public User getUser() { return user; }
}
