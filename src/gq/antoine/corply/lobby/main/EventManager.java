package gq.antoine.corply.lobby.main;

import gq.antoine.corply.commands.Commands;
import gq.antoine.corply.moderator.ModeratorCommands;
import gq.antoine.corply.utils.MethodUtils;
import gq.antoine.lobby.LobbyCosmetics;
import gq.antoine.lobby.LobbyEventManager;
import gq.antoine.lobby.LobbyNPCManager;
import gq.antoine.lobby.corplymember.NickCommand;
import gq.jeanyves.corply.spigot.api.CorplyAPI;
import gq.jeanyves.corply.spigot.api.PlayerData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class EventManager extends JavaPlugin implements Listener{
	
	/*
	 * CORPLY - Lobby v0.5.3 by UyL_Optimus13
	 * Copyright © 2016-2017 - All Rights Reserved.
	*/

	
	private static EventManager instance;
	public static FileConfiguration database;
	public static File dFile;
	private Location spawn;
	private HashMap <Player, Integer> players = new HashMap<Player, Integer>();
	
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
		final Player p = e.getPlayer();
		if(database.contains(p.getUniqueId().toString())){
			p.setDisplayName(database.getString(p.getUniqueId().toString()));
		};
		p.teleport(spawn);
		p.setGameMode(GameMode.ADVENTURE);
		MethodUtils.sendTitle(p, "§6CORPLY !", "§f§lBon jeu sur §6CORPLY §f§l!", 65);
		MethodUtils.sendTabList(p, "§6CORPLY !", "§f§lBon jeu sur §6CORPLY §f§l!");
		final PlayerData d = CorplyAPI.getAPI().getData(p);
		if(d.getRank() >= 75){
			p.setAllowFlight(true);
		}
		p.setPlayerListName(d.getTabName()); //Change nom dans tablist
		if (d.getRank() >= 50) {
			e.setJoinMessage(d.getDisplayName() + " a rejoint le jeu !");
		} else {
			e.setJoinMessage("");
		}
		final ScoreboardManager manager = Bukkit.getScoreboardManager();
		//Un scoreboard par personne, updaté tous les 5 tick
		players.put(p, Bukkit.getScheduler().scheduleSyncRepeatingTask(EventManager.getPlugin(), new Runnable() {
			@Override
			public void run(){
				Scoreboard corplyboard = manager.getNewScoreboard();
				Random r = new Random();
			    String sbObjName = "BITE" + r.nextInt(10000000);
		        Objective scoreboard = corplyboard.registerNewObjective(sbObjName, "dummy");
		        scoreboard.setDisplaySlot(DisplaySlot.SIDEBAR);
		        int ping = ((CraftPlayer) p).getHandle().ping;
		        Score pinger = scoreboard.getScore("§6> Ping : §a" + ping + " §6ms");
		        Score online = scoreboard.getScore("§6> Joueur(s) : §a" + Bukkit.getOnlinePlayers().size());
		        scoreboard.setDisplayName(" §f§lCORPLY §f- §6Network ");
			    pinger.setScore(0);
			    online.setScore(-2);
		        Score blank = scoreboard.getScore("§1 ");
		        blank.setScore(4);
		        Score score = scoreboard.getScore("§6> Rang : §a" + d.getPrefix());
		        score.setScore(3);      
		        Score blank1 = scoreboard.getScore("§2 ");
		        blank1.setScore(1);  
		        Score blank2 = scoreboard.getScore("§3 ");
		        blank2.setScore(-1);
		        Score blank3 = scoreboard.getScore("§4 ");
		        blank3.setScore(-3);    
		        Score score1 = scoreboard.getScore("§6> Serveur :");
		        score1.setScore(-4);
		        Score servername = scoreboard.getScore("§a" + Bukkit.getServerName());
		        servername.setScore(-5);
		        Score blank4 = scoreboard.getScore("§5 ");
		        blank4.setScore(-6);
		        Score score2 = scoreboard.getScore("§6> CORPLYCoins : §c" + d.getBalance());
		        score2.setScore(-7);
		        Score score3 = scoreboard.getScore("§6> CORPLYGold : §c" + d.getGold());
		        score3.setScore(-8);
		        p.setScoreboard(corplyboard);
			}
		}, 0L, 100L));
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
		pm.registerEvents(new Commands(), this);
		pm.registerEvents(new NickCommand(), this);
		pm.registerEvents(new LobbyEventManager(), this);
		pm.registerEvents(new LobbyCosmetics(), this);
		this.getCommand("nick").setExecutor(new NickCommand());
		this.getCommand("gm").setExecutor(new Commands());
		this.getCommand("npc").setExecutor(new LobbyNPCManager());
		this.getCommand("kick").setExecutor(new ModeratorCommands());
		this.getCommand("mute").setExecutor(new ModeratorCommands());
		spawn = new Location(Bukkit.getWorlds().get(0), 0, 257,0);
		

	   
	}
	
	public static EventManager getPlugin(){
		return instance;
	}
}