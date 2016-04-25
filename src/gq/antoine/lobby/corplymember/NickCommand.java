package gq.antoine.lobby.corplymember;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class NickCommand implements CommandExecutor, Listener {
	
		String prefix = "§8[§dCorplyMember - Utils§8]";
		
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
			if(cmd.getName().equalsIgnoreCase("nick")){
				Player p = (Player) sender;
				p.sendMessage("§6>> §cCette fonctionnalitée est en maintenance !");
				if(!(sender instanceof Player)){
					sender.sendMessage(ChatColor.GOLD+"[CorplyLobby]"+ChatColor.RED+" Cette commande est seulement utilisable par les joueurs !"); return true;
				}
				/*Player player = (Player) sender;
				if(args.length == 0){
					player.sendMessage(prefix+" §cAide :");
					player.sendMessage("§6/nick set <Pseudo> §8- §6Définis votre pseudo");
					player.sendMessage("§6/nick check §8- §6Vous permet de voir si vous êtes caché");
					player.sendMessage("§6/nick reset §8- §6Redéfinis votre véritable pseudo.");
				}else{
					if(args[0].equalsIgnoreCase("set")){
						if(args.length == 1){
							player.sendMessage(prefix + " §c§lErreur, §c/nick set <Pseudo>"); return true;
						}else{
							if(args.length == 2){
								if(player.hasPermission("nick.nick") || player.isOp()){
									EventManager.getMain().setNick(player, args[1]);
									player.sendMessage(prefix + " §6Votre pseudo à été changé en : §3" + args[1]);
								}
							}
						}
					}else if(args[0].equalsIgnoreCase("check")){
						if(args.length == 1){
							if(player.hasPermission("nick.check")){
								if(EventManager.getMain().hasNick(player)){
									player.sendMessage(prefix + " §6Votre pseudo est §aactif §6et est : §3" + EventManager.getMain().getData().getString(player.getUniqueId().toString()));
								}else{
									player.sendMessage(prefix + " §c§lErreur, vous n'êtes pas masqué !");
								}
							}
						}
					}else if(args[0].equalsIgnoreCase("reset")){
						if(args.length == 1){
							if(player.hasPermission("nick.reset")){
								if(EventManager.getMain().hasNick(player)){
									EventManager.getMain().resetNick(player);
									player.sendMessage(prefix + " §6Pseudo rétabli !");
								}else{
									player.sendMessage(prefix + " §c§lErreur, vous n'êtes pas masqué !");
								}
							}
						}
					}
				}
			}
			return false;
		}
		
		@EventHandler
		public void onJoin(PlayerJoinEvent e){
			Player p = e.getPlayer();
			if(EventManager.getMain().hasNick(p)){
				p.sendMessage("§c§l[ATTENTION] §cVous êtes toujours masqué par un pseudo !");
			}
		*/}
			return false;
		}
}
