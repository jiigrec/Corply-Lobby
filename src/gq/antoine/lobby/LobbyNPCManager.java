package gq.antoine.lobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import gq.antoine.corply.utils.MethodUtils;

public class LobbyNPCManager implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, 
			String[] args) {
		
		Player p = (Player) sender;
		if(label.equalsIgnoreCase("npc")){
			if(p.hasPermission("npc.admin")){
					Entity ent = p.getWorld().spawnEntity(p.getEyeLocation(), EntityType.VILLAGER);
					ent.setCustomName("§cNPC");
					ent.setCustomNameVisible(true);
					MethodUtils.disableAI(ent);
					p.sendMessage("§6"+ent.getName()+" §f§l§n"+ent.getType()+"§6 est apparue ! §7UUID: "+ent.getUniqueId());
			}else{
				p.sendMessage(MethodUtils.sendNoPermission());
			}
		}
		return false;
	}
}
