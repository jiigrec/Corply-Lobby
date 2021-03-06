package gq.antoine.corply.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class MethodUtils implements Listener {
	
	public static ArrayList<Player> isMuted = new ArrayList<Player>();
	public static ArrayList<OfflinePlayer> isMutedOffline = new ArrayList<OfflinePlayer>();
	
	static String System = "�7[�c�lSyst�me�7]";
	static String Error = getSystem()+" �c�lTu n'as pas la permission d'effectuer cela !";
	static String isMutedReason = getSystem()+" �c�lVous �tes actuellement mis en sourdine, vous ne pouvez pas parler !";
	static String isBannedReason = getSystem()+" �7Vous avez �t� �cbanni �7!";
	static String isKickedReason = getSystem()+" �7Vous avez �t� �ckick� �7!";
	
	public static String getSystem() {
		return System;
	}
	public static String sendNoPermission(){
		return Error;
	}
	public static String isMutedMessage(){
		return isMutedReason;
	}
	public static String isBannedMessage(){
		return isBannedReason;
	}
	public static String sendKickedMessage(){
		return isKickedReason;
	}

	
	/*
	@SuppressWarnings("deprecation")
	public static void setPrefix(Player p, String prefix){
		Scoreboard s = Bukkit.getScoreboardManager().getMainScoreboard();
		
		String teamname = p.getName();
		Team t = s.getTeam(teamname);
		if (t == null){
			t = s.registerNewTeam(teamname);
		}
		
		t.setDisplayName(prefix);
		t.addPlayer(p);
	} */  //TODO: Enlever ce code, inutilis� (nouveau syst�me pour TabList)
	
	/*public static void sendNMSChat(Player p, String text){
		IChatBaseComponent c = ChatSerializer.a
				("{\"text\": \"" + text + "\"}");
		
		PacketPlayOutChat packet = new PacketPlayOutChat(c);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
	} */ //TODO celui la aussi
	
	public static void sendActionBar(Player p, String text){
		IChatBaseComponent actionBarComp = ChatSerializer
				.a("{\"text\": \""+text+"\"}");
		
		PacketPlayOutChat actionBarPacket = new PacketPlayOutChat(actionBarComp, (byte)2);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(actionBarPacket);
	}
	
	public static void sendTabList(Player p, String header, String footer) {
		IChatBaseComponent tabTitle = ChatSerializer.a("{\"text\": \""+header+"\"}");
        IChatBaseComponent tabFoot = ChatSerializer.a("{\"text\": \""+footer+"\"}");
     
        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
        
        try {
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(headerPacket);
        }
	}
	
	public static void sendTitle(Player p, String title, String subtitle, Integer time){
		IChatBaseComponent titleComp = ChatSerializer
				.a("{\"text\": \""+title+"\"}");
		IChatBaseComponent subtitleComp = ChatSerializer
				.a("{\"text\": \""+subtitle+"\"}");
		
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleComp);
		PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitleComp);
		
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(titlePacket);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitlePacket);
		
		PacketPlayOutTitle timePacket = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, time, 20);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(timePacket);
	}
	
	public static void disableAI(Entity ent){
		net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) ent).getHandle();
		NBTTagCompound tag = nmsEntity.getNBTTag();
		
		if(tag == null){
			tag = new NBTTagCompound();
		}
		
		nmsEntity.c(tag);
		tag.setInt("NoAI", 1);
		nmsEntity.f(tag);
	}
	
	@SuppressWarnings("deprecation")
	public static void banPlayer(Player target, CommandSender p){
		target.kickPlayer("�7Vous avez �t� �cbanni �7!");
		target.setBanned(true);
		
	}
	
	public static void kickPlayer(Player target, String reason){
		target.kickPlayer(reason);
	}
	
	public static void sendTitle(Player p, String title, String subtitle, int time) {
		IChatBaseComponent titleComp = ChatSerializer
				.a("{\"text\": \""+title+"\"}");
		IChatBaseComponent subtitleComp = ChatSerializer
				.a("{\"text\": \""+subtitle+"\"}");
		
		PacketPlayOutTitle titlePacket = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleComp);
		PacketPlayOutTitle subtitlePacket = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitleComp);
		
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(titlePacket);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitlePacket);
		
		PacketPlayOutTitle timePacket = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, 20, time, 20);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(timePacket);
		
	}
	public static void setMuted(Player target){
		isMuted.add(target);
	}
	
	public static void setUnmuted(Player target){
		isMuted.remove(target);
	}
	
	@EventHandler
	public void isMutedChat(AsyncPlayerChatEvent e){
		Player target = e.getPlayer();
		if(isMuted.contains(target)){
			e.setCancelled(true);
			target.sendMessage(isMutedMessage());
		}else{
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void isBannedKick(PlayerLoginEvent e){
		Player p = e.getPlayer();
		if(p.isBanned() == true){
			e.disallow(Result.KICK_BANNED, isBannedMessage());	
		}else{
			e.allow();
		}
	}
}