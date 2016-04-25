package gq.antoine.corply.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import gq.antoine.corply.utils.MethodUtils;

public class Commands implements Listener, CommandExecutor{
	
	//86400 24H en seondes
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, 
			String[] args) {
		
		Player p = (Player) sender;
		if(p.hasPermission("lobby.gm") || p.isOp()){
			if(label.equalsIgnoreCase("gm")){
				if(args.length != 1){
					p.sendMessage(MethodUtils.getSystem()+" §c§lErreur, §c/gm [0|1|2|3]");
				}else{
					if(args[0].equalsIgnoreCase("0")){
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(MethodUtils.getSystem()+" §bMode de jeu : §e§lSurvie");
					}else if(args[0].equalsIgnoreCase("1")){
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(MethodUtils.getSystem()+" §bMode de jeu : §e§lCréatif");
					}else if(args[0].equalsIgnoreCase("2")){
						p.setGameMode(GameMode.ADVENTURE);
						p.sendMessage(MethodUtils.getSystem()+" §bMode de jeu : §e§lAventure");
					}else if(args[0].equalsIgnoreCase("3")){
						p.setGameMode(GameMode.SPECTATOR);
						p.sendMessage(MethodUtils.getSystem()+" §bMode de jeu : §e§lSpectateur");
					}
				}
			}
		}else{
			p.sendMessage(MethodUtils.sendNoPermission());
		}
		
		return false;
	}
}