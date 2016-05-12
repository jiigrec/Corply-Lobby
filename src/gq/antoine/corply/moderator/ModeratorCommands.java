package gq.antoine.corply.moderator;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import gq.antoine.corply.utils.MethodUtils;

public class ModeratorCommands implements CommandExecutor{
	
	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, 
			String[] args) {
		Player p = (Player) sender;
		if(p.hasPermission("m.kick")){
			if(label.equalsIgnoreCase("kick")){
				if(args.length < 1){
					p.sendMessage(MethodUtils.getSystem()+" §cPas assez d'arguments ! /kick <Joueur>");
				}else{
					Player target = Bukkit.getPlayerExact(args[0]);
					MethodUtils.kickPlayer(target, MethodUtils.sendKickedMessage());
					MethodUtils.sendActionBar(p, "§c§7"+target.getName()+" §7a été §ckické §7!");
					p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 20, 20);
					
				}
			}
		}else{
			p.sendMessage(MethodUtils.sendNoPermission());
			return true;
		}
		
		if(p.hasPermission("m.mute")){
			if(label.equalsIgnoreCase("mute")){
				Player target = Bukkit.getPlayerExact(args[0]);
				OfflinePlayer offlinetarget = Bukkit.getPlayerExact(args[0]);
				if(args.length < 1){
					p.sendMessage(MethodUtils.getSystem()+" §cPas assez d'arguments ! /mute <Joueur>");
				}else{
					if(target.isOnline()){
						if(MethodUtils.isMuted.contains(target)){
							MethodUtils.setUnmuted(target);
						}else{
							MethodUtils.setMuted(target);
							for(Player pls : Bukkit.getOnlinePlayers()){
								if(p.hasPermission("m.mute.recive")){
									p.sendMessage(MethodUtils.getSystem()+" §c"+target.getName()+" §7a été muté par "+p.getName());
									MethodUtils.sendActionBar(p, MethodUtils.getSystem()+" §c"+target.getName()+" §7a été muté par "+p.getName());
								}
							}
						}
					}else{
						p.sendMessage(MethodUtils.getSystem()+" §cErreur, le joueur doit être en ligne !");
					}
				}
			}
		}else{
			p.sendMessage(MethodUtils.sendNoPermission());
			return true;
		}
		
		return false;
	}
}
