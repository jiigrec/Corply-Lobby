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
import org.bukkit.scoreboard.Scoreboard;

import gq.antoine.corply.utils.MethodUtils;
import gq.jeanyves.corply.spigot.api.CorplyAPI;
import gq.jeanyves.corply.spigot.api.PlayerData;


public class LobbyEventManager implements Listener{
	
	
	public static Location spawn = new Location(Bukkit.getWorld("Lobby"), 448.471, 164, 1156.439);
	private HashMap <Player, Scoreboard> players = new HashMap<Player, Scoreboard>();
	public static Location jump = new Location(Bukkit.getWorld("Lobby"), 450.474, 157, 1114.464);
	
	@EventHandler 
	public void onBreakBlock(BlockBreakEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler 
	public void onQuit(PlayerQuitEvent e){
		e.setQuitMessage("");
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.teleport(spawn);
		p.setGameMode(GameMode.ADVENTURE);
		MethodUtils.sendTitle(p, "§6CORPLY !", "§f§lBon jeu sur §6CORPLY §f§l!", 65);
		MethodUtils.sendTabList(p, "§6CORPLY !", "§f§lBon jeu sur §6CORPLY §f§l!");
		PlayerData d = CorplyAPI.getAPI().getData(p);
		if(d.getRank() >= 75){
			p.setAllowFlight(true);
		}
		p.setPlayerListName(d.getTabName()); //Change nom dans tablist
		if (CorplyAPI.getAPI().getData(p).getRank() >= 50) {
			e.setJoinMessage(d.getDisplayName() + " a rejoint le jeu !");
		} else {
			e.setJoinMessage("");
		}
		Scoreboard s = Bukkit.getScoreboardManager().getNewScoreboard();
		Random r = new Random();
		String sbObjName = "BITE" + r.nextInt(10000000);
		Objective obj = s.registerNewObjective(sbObjName, "dummy");
		obj.setDisplayName("§6CORPLY !");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.getScore(Bukkit.getOfflinePlayer(d.getPrefix())).setScore(4);
		//obj.getScore(Bukkit.getOfflinePlayer("")).setScore(3);
		obj.getScore(Bukkit.getOfflinePlayer("§eCorply§lCoins §7: §b" + d.getBalance())).setScore(2);
		obj.getScore(Bukkit.getOfflinePlayer("§6Corply§lGold §7: §b" + d.getGold())).setScore(1);
		p.setScoreboard(s);
		players.put(p, s);
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