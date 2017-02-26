package net.Farscore.IPUtils.Spigot.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class IPUtils implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender commandSender, Command commandInstance, String commandCalled, String[] commandArgs) {
		
		if(commandCalled.equalsIgnoreCase("iputils"))
		{
			if(commandArgs.length == 0)
			{
				commandSender.sendMessage(ChatColor.AQUA + "IPUtils 1.0 by Farscore");
				// maybe? commandSender.sendMessage(ChatColor.GRAY + "/iputils config set <kickMessage/maxAccounts/timeOnlineTrack/blacklistMessage> <value>");
				commandSender.sendMessage(ChatColor.GRAY + "/iputils config reload");
				commandSender.sendMessage(ChatColor.GRAY + "/iputils config read");
				commandSender.sendMessage(ChatColor.GRAY + "/iputils track <player/ip>");
				commandSender.sendMessage(ChatColor.GRAY + "/iputils blacklist <player/ip>");
			}
			else if(commandArgs.length >= 2)
			{
				if(commandArgs[0].equalsIgnoreCase("track"))
				{
					if(!Tracking.execute(commandSender, commandArgs))
					{
						commandSender.sendMessage(ChatColor.RED + "You don't have sufficient permissions to do this!");
					}
				}
				else if(commandArgs[0].equalsIgnoreCase("blacklist"))
				{
					if(!BlacklistCmd.execute(commandSender, commandArgs))
					{
						commandSender.sendMessage(ChatColor.RED + "You don't have sufficient permissions to do this!");
					}
				}
				else if(commandArgs[0].equalsIgnoreCase("config"))
				{
					if(!ConfigCmd.execute(commandSender, commandArgs))
					{
						commandSender.sendMessage(ChatColor.RED + "You don't have sufficient permissions to do this!");
					}
				}
				else
				{
					commandSender.sendMessage(ChatColor.AQUA + "IPUtils 1.0 by Farscore");
					// maybe? commandSender.sendMessage(ChatColor.GRAY + "/iputils config set <kickMessage/maxAccounts/timeOnlineTrack/blacklistMessage> <value>");
					commandSender.sendMessage(ChatColor.GRAY + "/iputils config reload");
					commandSender.sendMessage(ChatColor.GRAY + "/iputils config read");
					commandSender.sendMessage(ChatColor.GRAY + "/iputils track <player/ip>");
					commandSender.sendMessage(ChatColor.GRAY + "/iputils blacklist <player/ip>");
				}
				
			}
		}
		
		return false;
	}
	
}
