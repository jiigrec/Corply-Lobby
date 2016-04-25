package gq.antoine.lobby;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class LobbyCosmetics implements Listener, CommandExecutor {

	static HashMap<Player, BukkitRunnable> taskheart;
	static HashMap<Player, Integer> timer;

	BukkitRunnable runnableheart;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender) {
			sender.sendMessage(ChatColor.GOLD + "[CorplyLobby]" + ChatColor.RED
					+ " Cette commande est seulement utilisable par les joueurs !");
			return true;
		} else {
			Player p = (Player) sender;
			if (label.equalsIgnoreCase("debugc")) {
				InventoryCosmetics(p);
			}
		}
		return false;
	}

	public void Heart(final Player p) {
		if (!taskheart.containsKey(p))
			timer.put(p, 12);
		taskheart.put(p, runnableheart = new BukkitRunnable() {

			public void run() {
				final Location l = p.getLocation();
				final Effect e = Effect.HEART;
				final World w = p.getWorld();
				l.setY(l.getY() + 2);
				final Location loc1 = new Location(w, l.getX() + 0.5D, l.getY(), l.getZ());
				final Location loc2 = new Location(w, l.getX() + 0.43D, l.getY(), l.getZ() + 0.25D);
				final Location loc3 = new Location(w, l.getX() + 0.25D, l.getY(), l.getZ() + 0.43D);
				final Location loc4 = new Location(w, l.getX(), l.getY(), l.getZ() + 0.5D);
				final Location loc5 = new Location(w, l.getX() - 0.25D, l.getY(), l.getZ() + 0.43D);
				final Location loc6 = new Location(w, l.getX() - 0.43D + 0.5D, l.getY(), l.getZ() + 0.25D);
				final Location loc7 = new Location(w, l.getX() - 0.5D, l.getY(), l.getZ());
				final Location loc8 = new Location(w, l.getX() - 0.43D, l.getY(), l.getZ() - 0.25D);
				final Location loc9 = new Location(w, l.getX() - 0.25D, l.getY(), l.getZ() - 0.43D);
				final Location loc10 = new Location(w, l.getX(), l.getY(), l.getZ() - 0.5D);
				final Location loc11 = new Location(w, l.getX() + 0.25D, l.getY(), l.getZ() - 0.43D);
				final Location loc12 = new Location(w, l.getX() + 0.43D, l.getY(), l.getZ() - 0.25D);
				if (timer.get(p) >= 12) {
					timer.put(p, timer.get(p) - 1);
					w.playEffect(loc1, e, 1);
				}

				if (timer.get(p) == 11)
					w.playEffect(loc2, e, 1);

				if (timer.get(p) == 10)

					w.playEffect(loc3, e, 1);

				if (timer.get(p) == 9)

					w.playEffect(loc4, e, 1);

				if (timer.get(p) == 8)

					w.playEffect(loc5, e, 1);

				if (timer.get(p) == 7)

					w.playEffect(loc6, e, 1);

				if (timer.get(p) == 6)

					w.playEffect(loc7, e, 1);

				if (timer.get(p) == 5)

					w.playEffect(loc8, e, 1);

				if (timer.get(p) == 4)

					w.playEffect(loc9, e, 1);

				if (timer.get(p) == 3)

					w.playEffect(loc10, e, 1);

				if (timer.get(p) == 2)

					w.playEffect(loc11, e, 1);

				if (timer.get(p) == 1)

					w.playEffect(loc12, e, 1);

				if (timer.get(p) == 0) {
					w.playEffect(l, e, 1);
					timer.put(p, timer.get(p) + 1);
				}
			}
		});
		taskheart.get(p).runTaskTimer(Bukkit.getPluginManager().getPlugin("CorplyLobby"), 0, 20);
	}

	public static void InventoryCosmetics(Player p) {
		Inventory cosmetics = Bukkit.createInventory(null, 9 * 5, "§0Cosmétiques");

		ItemStack coeur = new ItemStack(Material.RED_ROSE);
		ItemMeta coeurm = coeur.getItemMeta();
		coeurm.setDisplayName("§7>> §6Coeurs");
		coeur.setItemMeta(coeurm);
		cosmetics.setItem(12, coeur);
		
		ItemStack stop = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta stopm = stop.getItemMeta();
		stopm.setDisplayName("§7>> §6Arrêter les effets");
		stop.setItemMeta(stopm);
		cosmetics.setItem(13, stop);

		p.openInventory(cosmetics);
	}

	@EventHandler
	public void onInteractInventoryMain(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§0Cosmétiques")) {
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
				return;
			switch (e.getCurrentItem().getType()) {

			case RED_ROSE:
				Heart(p);
				p.sendMessage("lelelelelel");
				p.closeInventory();
				break;
			case REDSTONE_BLOCK:
				runnableheart.cancel();
				taskheart.remove(p);
				timer.remove(p);
				p.closeInventory();
				break;
			default:
				break;
			}
		}
	}
}