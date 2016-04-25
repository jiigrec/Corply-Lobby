package gq.antoine.lobby;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class LobbyChatCensor implements Listener{
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String message1 = e.getMessage();
		String message2 = message1.toLowerCase();
		String message3 = message2.replace(".", "");
		String message4 = message3.replace("|", "");
		String message5 = message4.replace(",", "");
		String message6 = message5.replace("&", "");
		String message7 = message6.replace(":", "");
		String message8 = message7.replaceAll(" ", "");
		String message9 = message8.replaceAll("(\\w)\\1+ ", "$1");
	
		if(message9.contains("noob") ||  message9.contains("ez") || message9.contains("rekt") || message9.contains("http")){
			e.setCancelled(true);
			p.sendMessage("§6Censure > §cVeuillez rester poli avec les autres joueurs.");
		}
	}
}
