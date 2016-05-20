package gq.antoine.lobby;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import gq.antoine.corply.lobby.main.EventManager;
import gq.antoine.corply.utils.MethodUtils;
import gq.jeanyves.corply.spigot.api.CorplyAPI;
import gq.jeanyves.corply.spigot.api.PlayerData;


public class LobbyEventManager implements Listener{
	
	
	public static Location spawn = new Location(Bukkit.getWorld("Lobby"), 448.471, 164, 1156.439);
	private HashMap <Player, Integer> players = new HashMap<Player, Integer>();
	public static Location jump = new Location(Bukkit.getWorld("Lobby"), 450.474, 157, 1114.464);
	
	@EventHandler 
	public void onBreakBlock(BlockBreakEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler 
	public void onQuit(PlayerQuitEvent e){
		Bukkit.getScheduler().cancelTask(players.get(e.getPlayer())); // Arrete la génération du scoreboard custom
		players.remove(e.getPlayer());
		e.setQuitMessage("");
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		final Player p = e.getPlayer();
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
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String msg = e.getMessage();
		PlayerData d = CorplyAPI.getAPI().getData(p);
		e.setFormat(d.getDisplayName() + msg);
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			if (e.getCause().equals(DamageCause.FALL)){	
				e.setCancelled(true);
			if (e.getCause().equals(DamageCause.VOID)){
				Player p = (Player) e.getEntity();
				p.teleport(spawn);
			}
			}
		}
	}
}