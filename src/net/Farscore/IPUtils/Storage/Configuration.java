package net.Farscore.IPUtils.Storage;

import java.util.HashMap;

public class Configuration {
	
	public static boolean isSpigot = false;
	public static HashMap<String, String> configProperties = new HashMap<String, String>();
	public static String consoleTag = "[IPUtils] ";
	
	public static boolean hasRetryedConfig = false;
	
	public static void loadConfigIntoMemory()
	{
		if(isSpigot)
		{
			for(String key : net.Farscore.IPUtils.Spigot.Core.plugin.getConfig().getKeys(false))
			{
				if(!key.contains("blacklist"))
				{
					configProperties.put(key, net.Farscore.IPUtils.Spigot.Core.plugin.getConfig().get(key).toString());
				}
			}
		}
		else
		{
			for(String key : net.Farscore.IPUtils.BungeeCord.Core.config.getConfig().getKeys())
			{
				if(!key.contains("blacklist"))
				{
					configProperties.put(key, net.Farscore.IPUtils.BungeeCord.Core.config.getConfig().get(key).toString());
				}
			}
		}
		
		if(hasRetryedConfig && !configProperties.containsKey("kickMessage"))
		{
			System.out.println(consoleTag + "Ouch, even after re-writing the default values I still couldn't read the file correctly. Do I have read and write permissions?");
		}
		else if(!configProperties.containsKey("kickMessage"))
		{
			configProperties.clear();
			hasRetryedConfig = true;
			System.out.println(consoleTag + "I couldn't read the configuration file correctly, re-writing default values...");
			rewrite();
		}
	}
	
	public static boolean isBlacklisted(String ip)
	{
		if(isSpigot)
		{
			return net.Farscore.IPUtils.Spigot.Core.plugin.getConfig().contains("blacklisted." + ip);
		}
		else
		{
			if(net.Farscore.IPUtils.BungeeCord.Core.config.getConfig().get("blacklisted." + ip) == null)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}
	
	public static void rewrite()
	{
		if(isSpigot)
		{
			net.Farscore.IPUtils.Spigot.Core.plugin.getConfig().set("kickMessage", "&cYou have too many accounts logged in with the same IP!");
			net.Farscore.IPUtils.Spigot.Core.plugin.getConfig().set("maxAccounts", 1);
			net.Farscore.IPUtils.Spigot.Core.plugin.getConfig().set("timeOnlineTrack", true);
			net.Farscore.IPUtils.Spigot.Core.plugin.getConfig().set("blacklistMessage", "&cYour IP is blacklisted from this server!");
			net.Farscore.IPUtils.Spigot.Core.plugin.getConfig().set("blacklisted.start", true);
			net.Farscore.IPUtils.Spigot.Core.plugin.saveConfig();
		}
		else
		{
			net.Farscore.IPUtils.BungeeCord.Core.config.getConfig().set("kickMessage", "&cYou have too many accounts logged in with the same IP!");
			net.Farscore.IPUtils.BungeeCord.Core.config.getConfig().set("maxAccounts", 1);
			net.Farscore.IPUtils.BungeeCord.Core.config.getConfig().set("timeOnlineTrack", true);
			net.Farscore.IPUtils.BungeeCord.Core.config.getConfig().set("blacklistMessage", "&cYour IP is blacklisted from this server!");
			net.Farscore.IPUtils.BungeeCord.Core.config.getConfig().set("blacklisted.start", true);
			net.Farscore.IPUtils.BungeeCord.Core.config.saveConfig();
		}
		
		loadConfigIntoMemory();
	}
	
	public static boolean getBooleanFromMemorySilently(String key)
	{
		if(configProperties.containsKey(key))
		{
			if(configProperties.get(key).equalsIgnoreCase("true") || configProperties.get(key).equalsIgnoreCase("false"))
			{
				return Boolean.parseBoolean(configProperties.get(key));
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	public static boolean getBooleanFromMemory(String key)
	{
		if(configProperties.containsKey(key))
		{
			if(configProperties.get(key).equalsIgnoreCase("true") || configProperties.get(key).equalsIgnoreCase("false"))
			{
				return Boolean.parseBoolean(configProperties.get(key));
			}
			else
			{
				System.out.println(consoleTag + "Invalid configuration value at " + key + "!");
				return false;
			}
		}
		else
		{
			System.out.println(consoleTag + "Couldn't get configuration value " + key + " from memory!");
			return false;
		}
	}
	
	public static boolean isInt(String s)
	{
	    return (s.lastIndexOf("-") == 0 && !s.equals("-0")) ? s.replace("-", "").matches(
	            "[0-9]+") : s.matches("[0-9]+");
	}
	
	public static Integer getIntegerFromMemory(String key)
	{
		if(configProperties.containsKey(key))
		{
			if(isInt(configProperties.get(key)))
			{
				return Integer.parseInt(configProperties.get(key));
			}
			else
			{
				System.out.println(consoleTag + "Invalid configuration value at " + key + "!");
				return 1;
			}
		}
		else
		{
			System.out.println(consoleTag + "Couldn't get configuration value " + key + " from memory!");
			return 1;
		}
	}
	
	public static String getStringFromMemory(String key)
	{
		if(configProperties.containsKey(key))
		{
			return configProperties.get(key);
		}
		else
		{
			System.out.println(consoleTag + "Couldn't get configuration value " + key + " from memory!");
			return "";
		}
	}
	
}
