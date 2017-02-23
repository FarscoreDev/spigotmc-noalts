package net.Farscore.IPUtils.Spigot;

import net.Farscore.IPUtils.Storage.BlacklistManager;
import net.Farscore.IPUtils.Storage.Configuration;
import net.Farscore.IPUtils.Storage.Storage;
import net.md_5.bungee.event.EventHandler;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class Events implements Listener {
	
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
