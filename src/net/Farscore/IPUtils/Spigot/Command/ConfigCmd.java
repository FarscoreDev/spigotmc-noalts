package net.Farscore.IPUtils.Spigot.Command;

import java.util.Map.Entry;

import net.Farscore.IPUtils.Storage.Configuration;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ConfigCmd {
	
	public static String parse(String[] longString, Integer startAt)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = startAt; i < longString.length; i++){
		sb.append(longString[i]).append(" ");
		}
		 
		String allArgs = sb.toString().trim();
		
		return allArgs;
	}
	
	public static boolean execute(CommandSender p, String[] passedArgs)
	{
		if(p.hasPermission("iputils.config.reload"))
		{
			if(passedArgs.length == 2)
			{
				if(passedArgs[1].equalsIgnoreCase("reload"))
				{
					Configuration.configProperties.clear();
					Configuration.loadConfigIntoMemory();
					
					p.sendMessage(ChatColor.GREEN + "The config has been reloaded!");
				}
				else if(passedArgs[1].equalsIgnoreCase("read"))
				{
					p.sendMessage(ChatColor.GRAY + "Listing all configuration values:");
					
					for(Entry<String, String> s : Configuration.configProperties.entrySet())
					{
						p.sendMessage(ChatColor.GREEN + s.getKey() + " with value " + s.getValue());
					}
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Invalid usage! " + "/iputils config reload");
				}
			}
			else
			{
				p.sendMessage(ChatColor.RED + "Invalid usage! " + "/iputils config reload");
			}
			
			return true;
		}
		
		return false;
	}
	
}
