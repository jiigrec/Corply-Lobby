package gq.antoine.lobby;

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

import gq.antoine.corply.utils.MethodUtils;
import gq.jeanyves.corply.spigot.api.CorplyAPI;
import gq.jeanyves.corply.spigot.api.PlayerData;


public class LobbyEventManager implements Listener{
	
	
	public static Location spawn = new Location(Bukkit.getWorld("Lobby"), 448.471, 164, 1156.439);
	
	public static Location jump = new Location(Bukkit.getWorld("Lobby"), 450.474, 157, 1114.464);
	
	@EventHandler 
	public void onBreakBlock(BlockBreakEvent e){
		e.setCancelled(true);
	}
	
	@EventHandler 
	public void onQuit(PlayerQuitEvent e){
		e.setQuitMessage("");
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.teleport(spawn);
		p.setGameMode(GameMode.ADVENTURE);
		MethodUtils.sendTitle(p, "§6CORPLY !", "§f§lBon jeu sur §6CORPLY §f§l!", 65);
		MethodUtils.sendTabList(p, "§6CORPLY !", "§f§lBon jeu sur §6CORPLY §f§l!");
		if(CorplyAPI.getAPI().getData(p).getRank() >= 75){
			p.setAllowFlight(true);
		}
		p.setPlayerListName(CorplyAPI.getAPI().getData(p).getTabName()); //Change nom dans tablist
		e.setJoinMessage(CorplyAPI.getAPI().getData(p).getDisplayName() + " a rejoint le jeu !");
    
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