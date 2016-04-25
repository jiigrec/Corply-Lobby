package gq.antoine.corply.bungee;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import gq.jeanyves.corply.spigot.api.CorplyAPI;

public class BungeeServerConnect implements Listener{
	
	public static void connectSpleef(Player p){
		CorplyAPI.getAPI().sendPlayer(p, "Spleef");
	}
	public static void connectWurlay(Player p){
		CorplyAPI.getAPI().sendPlayer(p, "Wurlay");
	}
	public static void connectFaction(Player p){
		CorplyAPI.getAPI().sendPlayer(p, "Faction");
	}
	public static void connectCreative(Player p) {
		CorplyAPI.getAPI().sendPlayer(p, "Creatif");
	}
}