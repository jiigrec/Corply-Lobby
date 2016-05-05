package gq.antoine.corply.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import gq.antoine.corply.bungee.BungeeServerConnect;
import gq.antoine.lobby.LobbyEventManager;
import gq.jeanyves.corply.spigot.api.CorplyAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

public class GUIs implements Listener {

	@EventHandler
	public void onDragItem(InventoryClickEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onMoveItem(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		InventoryJoinDefine(p);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		p.getInventory().clear();
	}

	@EventHandler
	public void onInteractItemMain(PlayerInteractEvent e) {
		e.setCancelled(true);
		Player p = e.getPlayer();
		if (e.getItem() != null && e.getItem().getType() == Material.COMPASS
				&& (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR))
			InventoryMainMenu(p);
	}

	@EventHandler
	public void onInteractItemShop(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getItem() != null && e.getItem().getType() == Material.GOLD_INGOT
				&& (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR))
			InventoryShopMenu(p);
	}

	@EventHandler
	public void onInteractItemInfo(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getItem() != null && e.getItem().getType() == Material.SKULL_ITEM
				&& (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR))
			InventoryInfoMenu(p);
	}

	@EventHandler
	public void onInteractItemOptions(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getItem() != null && e.getItem().getType() == Material.REDSTONE_COMPARATOR
				&& (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR))
			p.sendMessage("§6>> §cCette fonctionalitée est en développement !");
	}

	@EventHandler
	public void onInteractInventoryMain(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§0Menu principal")) {
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
				return;
			switch (e.getCurrentItem().getType()) {

			case STAINED_GLASS:
				e.setCancelled(true);
				break;
			case SKULL_ITEM:
				e.setCancelled(true);
				break;
			case SNOW_BALL:
				e.setCancelled(true);
				BungeeServerConnect.connectSpleef(p);
				break;
			case BOOK_AND_QUILL:
				e.setCancelled(true);
				BungeeServerConnect.connectWurlay(p);
				break;
			case OBSIDIAN:
				e.setCancelled(true);
				BungeeServerConnect.connectFaction(p);
				break;
			case BED:
				e.setCancelled(true);
				p.teleport(LobbyEventManager.spawn);
				p.closeInventory();
				break;
			case FEATHER:
				e.setCancelled(true);
				p.teleport(LobbyEventManager.jump);
				p.closeInventory();
				break;
			case BANNER:
				e.setCancelled(true);
				break;
			case GRASS:
				e.setCancelled(true);
				BungeeServerConnect.connectCreative(p);
				break;
			case GOLDEN_APPLE:
				e.setCancelled(true);
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 20, 20);
				p.sendMessage("§7§l>> §cCes modes de jeux ne sont pas encore disponibles !");
				/** InventoryUHC(p); */
				break;
			case RED_ROSE:
				e.setCancelled(true);
				break;
			case PAPER:
				e.setCancelled(true);
				break;
			case CHAINMAIL_CHESTPLATE:
				e.setCancelled(true);
				break;
			case YELLOW_FLOWER:
				e.setCancelled(true);
				break;
			case NAME_TAG:
				e.setCancelled(true);
				break;
			case NETHER_STAR:
				e.setCancelled(true);
				p.closeInventory();
				p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 20, 20);
				p.sendMessage("§7§l>> §cCes modes de jeux ne sont pas encore disponibles !");
				/** InventoryClassics(p); */
				break;
			default:
			}
		}
	}

	@EventHandler
	public void onInteractInventoryShop(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§0Boutique")) {
			@SuppressWarnings("unused")
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
				return;
			switch (e.getCurrentItem().getType()) {

			case STAINED_GLASS:
				e.setCancelled(true);
				break;
			case SKULL_ITEM:
				e.setCancelled(true);
				break;
			case IRON_BOOTS:
				e.setCancelled(true);
				/*
				 * p.closeInventory();
				 * p.sendMessage("§f§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				 * p.sendMessage(" "); sendMiniVIPMessage(p); p.sendMessage(" "
				 * ); p.sendMessage("§f§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				 */
				break;
			case GOLD_BOOTS:
				e.setCancelled(true);
				/*
				 * p.closeInventory();
				 * p.sendMessage("§f§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				 * p.sendMessage(" "); sendVIPMessage(p); p.sendMessage(" ");
				 * p.sendMessage("§f§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				 */
				break;
			case DIAMOND_BOOTS:
				e.setCancelled(true);
				/*
				 * p.closeInventory();
				 * p.sendMessage("§f§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				 * p.sendMessage(" "); sendSOONMessage(p); p.sendMessage(" ");
				 * p.sendMessage("§f§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				 */
				break;
			default:
			}
		}
	}

	@EventHandler
	public void onInteractInventoryInfo(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§0Informations")) {
			@SuppressWarnings("unused")
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
				return;
			switch (e.getCurrentItem().getType()) {

			case SKULL_ITEM:
				e.setCancelled(true);
				break;
			case BLAZE_POWDER:
				e.setCancelled(true);
				break;
			case WORKBENCH:
				e.setCancelled(true);
				break;
			case ENCHANTED_BOOK:
				e.setCancelled(true);
				break;
			default:
			}
		}
	}

	@EventHandler
	public void onInteractInventoryUHC(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§0Espace UHC : ")) {
			@SuppressWarnings("unused")
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
				return;
			switch (e.getCurrentItem().getType()) {

			case SKULL_ITEM:
				e.setCancelled(true);
				break;
			case BLAZE_POWDER:
				e.setCancelled(true);
				break;
			case WORKBENCH:
				e.setCancelled(true);
				break;
			case ENCHANTED_BOOK:
				e.setCancelled(true);
				break;
			default:
			}
		}
	}

	@EventHandler
	public void onInteractInventoryOptions(InventoryClickEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("§0Paramètres")) {
			@SuppressWarnings("unused")
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
				return;
			switch (e.getCurrentItem().getType()) {

			case SKULL_ITEM:
				e.setCancelled(true);
				break;
			case BLAZE_POWDER:
				e.setCancelled(true);
				break;
			case WORKBENCH:
				e.setCancelled(true);
				break;
			case ENCHANTED_BOOK:
				e.setCancelled(true);
				break;
			default:
			}
		}
	}

	@SuppressWarnings("unused")
	private void sendMiniVIPMessage(Player p) {
		IChatBaseComponent c = ChatSerializer.a(
				"{\"text\":\"Clique ici pour obtenir le grade MiniVIP !\",\"color\":\"gold\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://corply.eu/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Clique pour ouvrir la boutique !\",\"color\":\"red\"}]}}}");
		PacketPlayOutChat packet = new PacketPlayOutChat(c);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}

	@SuppressWarnings("unused")
	private void sendVIPMessage(Player p) {
		IChatBaseComponent c = ChatSerializer.a(
				"{\"text\":\"Clique ici pour obtenir le grade VIP !\",\"color\":\"gold\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://corply.eu/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Clique pour ouvrir la boutique !\",\"color\":\"red\"}]}}}");
		PacketPlayOutChat packet = new PacketPlayOutChat(c);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}

	@SuppressWarnings("unused")
	private void sendSOONMessage(Player p) {
		IChatBaseComponent c = ChatSerializer.a(
				"{\"text\":\"Clique ici pour obtenir le grade ??? !\",\"color\":\"gold\",\"bold\":true,\"underlined\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"http://corply.eu/\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Clique pour ouvrir la boutique !\",\"color\":\"red\"}]}}}");
		PacketPlayOutChat packet = new PacketPlayOutChat(c);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	}

	@SuppressWarnings("unused")
	private void InventoryUHC(Player p) {
		Inventory UHC = Bukkit.createInventory(null, 9 * 5, "§0Espace UHC : ");

		ItemStack tauperun = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta tauperunm = tauperun.getItemMeta();
		tauperunm.setDisplayName("§7>> §6TaupeRun §c(En développement...)");
		tauperun.setItemMeta(tauperunm);
		UHC.setItem(11, tauperun);

		ItemStack uhcrun = new ItemStack(Material.POTION);
		ItemMeta uhcrunm = uhcrun.getItemMeta();
		uhcrunm.setDisplayName("§7>> §6UHCRun §c(En développement...)");
		uhcrun.setItemMeta(uhcrunm);
		UHC.setItem(12, uhcrun);

		ItemStack uhc = new ItemStack(Material.APPLE);
		ItemMeta uhcm = uhc.getItemMeta();
		uhcm.setDisplayName("§7>> §6UHC §c(En développement...)");
		uhc.setItemMeta(uhcm);
		UHC.setItem(13, uhc);

		p.openInventory(UHC);
	}

	private void InventoryInfoMenu(Player p) {
		Inventory info = Bukkit.createInventory(null, 9 * 3, "§0Informations");

		ItemStack cosmetiques = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta cosmetiquesm = cosmetiques.getItemMeta();
		cosmetiquesm.setDisplayName("§7>> §6Cosmétiques §c(En développement)");
		cosmetiques.setItemMeta(cosmetiquesm);
		info.setItem(12, cosmetiques);

		ItemStack stats = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta statsm = stats.getItemMeta();
		statsm.setDisplayName("§7>> §6Statistiques §c(En développement)");
		stats.setItemMeta(statsm);
		info.setItem(14, stats);

		ItemStack achivements = new ItemStack(Material.WORKBENCH);
		ItemMeta achivementsm = achivements.getItemMeta();
		achivementsm.setDisplayName("§7>> §6Achivements §c(En développement)");
		achivements.setItemMeta(achivementsm);
		info.setItem(13, achivements);

		ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) playerhead.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName("§6" + p.getName());
		ArrayList<String> infop = new ArrayList<String>();
		infop.add("§7>> §6Grade : " + CorplyAPI.getAPI().getData(p).getPrefix());
		infop.add(" ");
		infop.add("§7>> §eCorply§lCoins §7: §bBientôt");
		infop.add("§7>> §6Corply§lGold §7: §bBientôt");
		infop.add(" ");
		infop.add("§7>> §dB§co§4o§1s§3t§ee§8r §7: §bBientôt");
		meta.setLore(infop);
		playerhead.setItemMeta(meta);
		info.setItem(4, playerhead);

		p.openInventory(info);
	}

	public void InventoryMainMenu(Player p) {
		Inventory main = Bukkit.createInventory(null, 9 * 5, "§0Menu principal");

		ItemStack sp = new ItemStack(Material.THIN_GLASS);
		ItemMeta spm = sp.getItemMeta();
		spm.setDisplayName(" ");
		sp.setItemMeta(spm);
		main.setItem(18, sp);
		main.setItem(19, sp);
		main.setItem(20, sp);
		main.setItem(21, sp);
		main.setItem(22, sp);
		main.setItem(23, sp);
		main.setItem(24, sp);
		main.setItem(25, sp);
		main.setItem(26, sp);

		if(CorplyAPI.getAPI().getData(p).getRank() >= 50){ 
			 ItemStack staffservers = new ItemStack(Material.NAME_TAG);
			 ItemMeta staffserversm = staffservers.getItemMeta();
			 staffserversm.setDisplayName("§7>> §6Serveurs WhiteListés");
			 staffservers.setItemMeta(staffserversm);
			 main.setItem(44, staffservers); 
		 }else{ 
			 main.setItem(44, null); p.openInventory(main);
		 }

		ItemStack classics = new ItemStack(Material.NETHER_STAR);
		ItemMeta classicsm = classics.getItemMeta();
		classicsm.setDisplayName("§7>> §6Les classiques");
		classics.setItemMeta(classicsm);
		main.setItem(2, classics);

		ItemStack hg = new ItemStack(Material.STONE_SWORD);
		ItemMeta hgm = hg.getItemMeta();
		hgm.setDisplayName("§7>> §6Hunger-Games §c(En développement...)");
		hg.setItemMeta(hgm);
		main.setItem(11, hg);

		ItemStack uhcspace = new ItemStack(Material.GOLDEN_APPLE);
		ItemMeta uhcspacem = uhcspace.getItemMeta();
		uhcspacem.setDisplayName("§7>> §6Espace UHC");
		uhcspace.setItemMeta(uhcspacem);
		main.setItem(6, uhcspace);

		ItemStack creatif = new ItemStack(Material.GRASS);
		ItemMeta creatifm = creatif.getItemMeta();
		creatifm.setDisplayName("§7>> §6Créatif §c(Prochainement...)");
		creatif.setItemMeta(creatifm);
		main.setItem(5, creatif);

		ItemStack jump = new ItemStack(Material.FEATHER);
		ItemMeta jumpm = jump.getItemMeta();
		jumpm.setDisplayName("§7>> §6Jump/Parcours");
		jump.setItemMeta(jumpm);
		main.setItem(30, jump);

		ItemStack spawn = new ItemStack(Material.BED);
		ItemMeta spawnm = spawn.getItemMeta();
		spawnm.setDisplayName("§7>> §6Spawn");
		spawn.setItemMeta(spawnm);
		main.setItem(31, spawn);

		ItemStack vipspace = new ItemStack(Material.DIAMOND);
		ItemMeta vipspacem = vipspace.getItemMeta();
		vipspacem.setDisplayName("§7>> §6Espace VIP");
		vipspace.setItemMeta(vipspacem);
		main.setItem(32, vipspace);

		ItemStack wurlay = new ItemStack(Material.BOOK_AND_QUILL);
		ItemMeta wurlaym = wurlay.getItemMeta();
		wurlaym.setDisplayName("§7>> §6Wurlay §c(Prochainement...)");
		wurlay.setItemMeta(wurlaym);
		main.setItem(14, wurlay);

		ItemStack spleef = new ItemStack(Material.SNOW_BALL);
		ItemMeta spleefm = spleef.getItemMeta();
		spleefm.setDisplayName("§7>> §6Spleef");
		spleef.setItemMeta(spleefm);
		main.setItem(4, spleef);

		ItemStack onevsone = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta onevsonem = onevsone.getItemMeta();
		onevsonem.setDisplayName("§7>> §61 VS 1 §c(En dévelopement...)");
		onevsone.setItemMeta(onevsonem);
		main.setItem(12, onevsone);

		ItemStack faction = new ItemStack(Material.OBSIDIAN);
		ItemMeta factionm = faction.getItemMeta();
		factionm.setDisplayName("§7>> §6Faction §c§l(1.9 Only)");
		faction.setItemMeta(factionm);
		main.setItem(3, faction);

		ItemStack pvz = new ItemStack(Material.YELLOW_FLOWER);
		ItemMeta pvzm = pvz.getItemMeta();
		pvzm.setDisplayName("§7>> §6Plants VS Zombies §c(En dévelopement...)");
		pvz.setItemMeta(pvzm);
		main.setItem(13, pvz);

		ItemStack guild = new ItemStack(Material.BANNER);
		ItemMeta guildm = guild.getItemMeta();
		guildm.setDisplayName("§7>> §6Guildes §c(Prochainement...)");
		guild.setItemMeta(guildm);
		main.setItem(39, guild);

		ItemStack amis = new ItemStack(Material.RED_ROSE);
		ItemMeta amism = amis.getItemMeta();
		amism.setDisplayName("§7>> §6Amis §c(Prochainement...)");
		amis.setItemMeta(amism);
		main.setItem(40, amis);

		ItemStack info = new ItemStack(Material.PAPER);
		ItemMeta infom = info.getItemMeta();
		infom.setDisplayName("§7>> §6Informations");
		ArrayList<String> infos = new ArrayList<String>();
		infos.add(" ");
		infos.add("§7>> §3Notre §6Forum : http://www.corply.eu/forum");
		infos.add("§7>> §3Notre §bTwitter : @CORPLY_gaming");
		infos.add("§7>> §3Notre §cchaîne YouTube : CORPLY");
		infom.setLore(infos);
		info.setItemMeta(infom);
		main.setItem(41, info);

		ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) playerhead.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName("§6" + p.getName());
		ArrayList<String> infop = new ArrayList<String>();
		/**
		 * infop.add("§7>> §6Grade : "
		 * +CorplyAPI.getAPI().getData(p).getPrefix());
		 */
		infop.add(" ");
		infop.add("§7>> §eCorply§lCoins §7: §bBientôt");
		infop.add("§7>> §6Corply§lGold §7: §bBientôt");
		infop.add(" ");
		infop.add("§7>> §dB§co§4o§1s§3t§ee§8r §7: §bBientôt");
		meta.setLore(infop);
		playerhead.setItemMeta(meta);
		main.setItem(36, playerhead);

		p.openInventory(main);
	}

	public void InventoryShopMenu(Player p) {
		Inventory shop = Bukkit.createInventory(null, 9 * 3, "§0Boutique");

		ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) playerhead.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName("§6" + p.getName());
		ArrayList<String> infop = new ArrayList<String>();
		infop.add("§7>> §6Grade : " + CorplyAPI.getAPI().getData(p).getPrefix());
		infop.add(" ");
		infop.add("§7>> §eCorply§lCoins §7: §bBientôt");
		infop.add("§7>> §6Corply§lGold §7: §bBientôt");
		infop.add(" ");
		infop.add("§7>> §dB§co§4o§1s§3t§ee§8r §7: §bBientôt");
		meta.setLore(infop);
		playerhead.setItemMeta(meta);
		shop.setItem(18, playerhead);

		ItemStack ranks = new ItemStack(Material.DIAMOND);
		ItemMeta ranksm = ranks.getItemMeta();
		ranksm.setDisplayName("§7>> §6Grades");
		ranks.setItemMeta(ranksm);
		shop.setItem(12, ranks);

		ItemStack boosters = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta boostersm = boosters.getItemMeta();
		boostersm.setDisplayName("§7>> §6Boosters");
		boosters.setItemMeta(boostersm);
		shop.setItem(14, boosters);
	}

	@SuppressWarnings("unused")
	private void InventoryOptionsMenu(Player p) {
		Inventory info = Bukkit.createInventory(null, 9 * 3, "§0Paramètres");

		ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) playerhead.getItemMeta();
		meta.setOwner(null);
		meta.setDisplayName("Visibilitée des joueurs");
		playerhead.setItemMeta(meta);
		info.setItem(1, playerhead);

		ItemStack active = new ItemStack(Material.GLASS, 1, (short) 3);
		ItemMeta activem = active.getItemMeta();
		activem.setDisplayName("§7>> §6Achivements §c(En développement)");
		active.setItemMeta(activem);
		info.setItem(3, active);

		p.openInventory(info);
	}

	public void InventoryJoinDefine(Player p) {
		ItemStack menu = new ItemStack(Material.COMPASS);
		ItemMeta menum = menu.getItemMeta();
		menum.setDisplayName("§6Menu principal §7(Clique droit !)");
		menu.setItemMeta(menum);
		p.getInventory().setItem(4, menu);

		ItemStack shop = new ItemStack(Material.GOLD_INGOT);
		ItemMeta shopm = shop.getItemMeta();
		shopm.setDisplayName("§6Boutique §7(Clique droit !)");
		shop.setItemMeta(shopm);
		p.getInventory().setItem(1, shop);

		ItemStack playerhead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) playerhead.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName("§6Informations §7(Clique droit !)");
		playerhead.setItemMeta(meta);
		p.getInventory().setItem(7, playerhead);

		ItemStack options = new ItemStack(Material.REDSTONE_COMPARATOR);
		ItemMeta optionsm = options.getItemMeta();
		optionsm.setDisplayName("§6Paramètres §7(Clique droit !)");
		options.setItemMeta(optionsm);
		p.getInventory().setItem(8, options);
	}
}