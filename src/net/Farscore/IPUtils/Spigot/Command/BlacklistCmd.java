package net.Farscore.IPUtils.Spigot.Command;

import net.Farscore.IPUtils.Lookup.Lookup;
import net.Farscore.IPUtils.Lookup.LookupData;
import net.Farscore.IPUtils.Storage.Configuration;
import net.Farscore.IPUtils.Storage.Storage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlacklistCmd {
	
	public static boolean validIP (String ip) {
	    try {
	        if ( ip == null || ip.isEmpty() ) {
	            return false;
	        }

	        String[] parts = ip.split( "\\." );
	        if ( parts.length != 4 ) {
	            return false;
	        }

	        for ( String s : parts ) {
	            int i = Integer.parseInt( s );
	            if ( (i < 0) || (i > 255) ) {
	                return false;
	            }
	        }
	        if ( ip.endsWith(".") ) {
	            return false;
	        }

	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
	
	public static boolean execute(CommandSender p, String[] passedArgs)
	{
		if(p.hasPermission("iputils.blacklist"))
		{
			if(passedArgs.length == 2)
			{
				if(Storage.playerStorage.map.containsKey(passedArgs[1]))
				{
					String ipOfPlayer = Storage.findIp(passedArgs[1]);
					
					p.sendMessage(ChatColor.GREEN + "You just blacklisted the IP Address " + ipOfPlayer + "!");
					Configuration.addBlacklist(ipOfPlayer);
					
					Bukkit.getPlayer(Storage.playerStorage.map.get(passedArgs[1])._name).kickPlayer(Configuration.getStringFromMemory("blacklistMessage"));
					
					for(Player staff : Bukkit.getOnlinePlayers())
					{
						if(staff.hasPermission("iputils.blacklist.look"))
						{
							staff.sendMessage(ChatColor.GREEN + p.getName() + " just blacklisted " + ipOfPlayer);
						}
					}
				}
				else
				{
					if(validIP(passedArgs[1]))
					{
						p.sendMessage(ChatColor.GREEN + "You just blacklisted the IP Address " + passedArgs[1] + "!");
						Configuration.addBlacklist(passedArgs[1]);
						
						for(Player staff : Bukkit.getOnlinePlayers())
						{
							if(staff.hasPermission("iputils.blacklist.look"))
							{
								staff.sendMessage(ChatColor.GREEN + p.getName() + " just blacklisted " + passedArgs[1]);
							}
						}
					}
					else
					{
						p.sendMessage(ChatColor.RED + "Invalid IP Address!");
					}
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "Invalid usage! " + "/iputils blacklist <player/ip>");
			}
			
			return true;
		}
		
		return false;
	}
	
}
