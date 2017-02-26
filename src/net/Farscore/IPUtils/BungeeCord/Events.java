package net.Farscore.IPUtils.BungeeCord;

import net.Farscore.IPUtils.Storage.BlacklistManager;
import net.Farscore.IPUtils.Storage.Configuration;
import net.Farscore.IPUtils.Storage.PlayerLink;
import net.Farscore.IPUtils.Storage.Storage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Events implements Listener {
	
	@EventHandler
	public void onJoin(PostLoginEvent e)
	{
		Storage.playerStorage.put(e.getPlayer().getName(), new PlayerLink(e.getPlayer().getName(), e.getPlayer().getAddress().getAddress().getHostAddress()));
	}
	
	@EventHandler
	public void onQuit(PlayerDisconnectEvent e)
	{
		if(Storage.playerStorage.map.containsKey(e.getPlayer().getName()))
		{
			Storage.playerStorage.map.remove(e.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void onLogin(PreLoginEvent e)
	{
		if(Storage.returnMatchedIPs(e.getConnection().getAddress().getAddress().getHostAddress()) >= Configuration.getIntegerFromMemory("maxAccounts"))
		{
			e.setCancelReason(ChatColor.translateAlternateColorCodes('&', Configuration.getStringFromMemory("kickMessage")));
			e.setCancelled(true);
		}
		
		if(BlacklistManager.checkBlacklist(e.getConnection().getAddress().getAddress().getHostAddress()))
		{
			e.setCancelReason(ChatColor.translateAlternateColorCodes('&', Configuration.getStringFromMemory("blacklistMessage")));
			e.setCancelled(true);
		}
	}
	
}
