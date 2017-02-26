package net.Farscore.IPUtils.BungeeCord.Command;

import net.Farscore.IPUtils.Lookup.Lookup;
import net.Farscore.IPUtils.Lookup.LookupData;
import net.Farscore.IPUtils.Storage.Storage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;

public class Tracking {
	
	public static boolean execute(CommandSender p, String[] passedArgs)
	{
		if(p.hasPermission("iputils.tracking"))
		{
			if(passedArgs.length == 2)
			{
				String possibleIp = Storage.findIp(passedArgs[0]);
				
				if(possibleIp == null)
				{
					p.sendMessage(ChatColor.RED + "The player or IP is not online!");
				}
				else
				{
					p.sendMessage(ChatColor.GRAY + "Lookup for IP " + ChatColor.BLUE + possibleIp);
					LookupData data = Lookup.giveLookupData(possibleIp);
					
					p.sendMessage(ChatColor.GREEN + "Found " + data.getTotal() + " player" + ((data.getTotal() > 1) ? "s" : "") + " connected");
					
					for(String o : data.getData())
					{
						p.sendMessage(o);
					}
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "Invalid usage! " + "/iputils tracking <player/ip>");
			}
			
			return true;
		}
		
		return false;
	}
	
}
