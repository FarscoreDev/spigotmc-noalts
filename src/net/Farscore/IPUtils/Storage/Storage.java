package net.Farscore.IPUtils.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class Storage {
		
	public static DuplicateMap<String, PlayerLink> playerStorage = new DuplicateMap<String, PlayerLink>();
	
	public static Integer returnMatchedIPs(String ip)
	{
		Integer found = 0;
		
		for(Entry<String, PlayerLink> o : playerStorage.entrySet())
		{
			if(o.getValue()._ip.equalsIgnoreCase(ip))
			{
				found++;
			}
		}
		
		return found;
	}
	
	public static String findIp(String input)
	{
		if(returnMatchedIPs(input) == 0)
		{
			if(playerStorage.map.containsKey(input))
			{
				return playerStorage.map.get(input)._ip;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return input;
		}
	}
	
}
