package net.Farscore.IPUtils.Spigot;

import net.Farscore.IPUtils.Storage.BlacklistManager;
import net.Farscore.IPUtils.Storage.Configuration;
import net.Farscore.IPUtils.Storage.PlayerLink;
import net.Farscore.IPUtils.Storage.Storage;
import net.md_5.bungee.event.EventHandler;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Storage.playerStorage.put(e.getPlayer().getName(), new PlayerLink(e.getPlayer().getName(), e.getPlayer().getAddress().getAddress().getHostAddress()));
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		if(Storage.playerStorage.map.containsKey(e.getPlayer().getName()))
		{
			Storage.playerStorage.map.remove(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent e)
	{
		if(Storage.returnMatchedIPs(e.getAddress().getHostAddress()) >= Configuration.getIntegerFromMemory("maxAccounts"))
		{
			e.disallow(Result.KICK_OTHER, ChatColor.translateAlternateColorCodes('&', Configuration.getStringFromMemory("kickMessage")));
		}
		
		if(BlacklistManager.checkBlacklist(e.getAddress().getHostAddress()))
		{
			e.disallow(Result.KICK_OTHER, ChatColor.translateAlternateColorCodes('&', Configuration.getStringFromMemory("blacklistMessage")));
		}
	}
	
}
