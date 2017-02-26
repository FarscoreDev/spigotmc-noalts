package net.Farscore.IPUtils.BungeeCord.Command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class IPUtils extends Command {

	public IPUtils()
	{
		super("iputils");
	}

	@Override
	public void execute(CommandSender commandSender, String[] commandArgs) {
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
	
}
