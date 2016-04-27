package gq.antoine.corply.lobby.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import gq.antoine.corply.commands.Commands;
import gq.antoine.corply.gui.GUIs;
import gq.antoine.corply.moderator.ModeratorCommands;
import gq.antoine.corply.utils.MethodUtils;
import gq.antoine.lobby.LobbyChatCensor;
import gq.antoine.lobby.LobbyCosmetics;
import gq.antoine.lobby.LobbyEventManager;
import gq.antoine.lobby.LobbyNPCManager;
import gq.antoine.lobby.corplymember.NickCommand;
import net.md_5.bungee.api.ChatColor;

public class EventManager extends JavaPlugin implements Listener{
	
	/*
	 * CORPLY - Lobby v0.5.3 by UyL_Optimus13
	 * Copyright © 2016-2017 - All Rights Reserved.
	*/

	
	private static EventManager instance;
	
	public static FileConfiguration database;
	public static File dFile;
	
	public static void setupFiles(){
		if(!instance.getDataFolder().exists()) instance.getDataFolder().mkdirs();
		
		dFile = new File(instance.getDataFolder(), "database.yml");
		if(!dFile.exists()){
			try{
				dFile.createNewFile();
			}catch(IOException ex){
				Bukkit.getLogger().warning("Impossible de créer le ficher, plugin désactivé !");
				Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("CorplyLobby"));
			}
		}
		database = YamlConfiguration.loadConfiguration(dFile);
	}
	
	public FileConfiguration getData(){
		return database;
	}
	
	
	@EventHandler
	public void onPlayerJoinDatabase(PlayerJoinEvent e){
		Player player = e.getPlayer();
		if(database.contains(player.getUniqueId().toString())){
			player.setDisplayName(database.getString(player.getUniqueId().toString()));
		}
	}
	
	public void save(){
		try{
			EventManager.database.save(EventManager.dFile);
		}catch(IOException ex){
			Bukkit.getLogger().warning("Impossible de sauvgarder le fichier, plugin désactivé !");
			Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("CorplyLobby"));
		}
	}
	
	public void setNick(Player player, String nick){
		EventManager.database.set(player.getUniqueId().toString(), nick);
		save();
		player.setDisplayName(nick + ChatColor.WHITE);
		player.setPlayerListName(nick);
	}
	
	public void resetNick(Player player){
		EventManager.database.set(player.getUniqueId().toString(), null);
		save();
		player.setDisplayName(null);
		player.setPlayerListName(null);
	}
	
	public boolean hasNick(Player player){
		return EventManager.database.contains(player.getUniqueId().toString());
	}
	

	public void onEnable() {
		instance = this;
		
		setupFiles();
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(ChatColor.GOLD+"[CorplyLobby] "+ChatColor.RED+"Plugin ON !");
		PluginManager pm = Bukkit.getServer().getPluginManager();
		Bukkit.getPluginManager().registerEvents(this, this);
		pm.registerEvents(new MethodUtils(), this);
		pm.registerEvents(new GUIs(), this);
		pm.registerEvents(new Commands(), this);
		pm.registerEvents(new NickCommand(), this);
		pm.registerEvents(new LobbyEventManager(), this);
		pm.registerEvents(new LobbyCosmetics(), this);
		pm.registerEvents(new LobbyChatCensor(), this);
		this.getCommand("nick").setExecutor(new NickCommand());
		this.getCommand("gm").setExecutor(new Commands());
		this.getCommand("npc").setExecutor(new LobbyNPCManager());
		this.getCommand("kick").setExecutor(new ModeratorCommands());
		this.getCommand("mute").setExecutor(new ModeratorCommands());
		this.getCommand("ban").setExecutor(new ModeratorCommands());
	}
	
	public static EventManager getPlugin(){
		return instance;
	}
}