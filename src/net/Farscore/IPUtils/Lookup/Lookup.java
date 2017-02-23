package net.Farscore.IPUtils.Lookup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.Farscore.IPUtils.Storage.Configuration;
import net.Farscore.IPUtils.Storage.PlayerLink;
import net.Farscore.IPUtils.Storage.Storage;

public class Lookup {
	
	public static LookupData giveLookupData(String ip)
	{
		Integer found = 0;
		ArrayList<String> output = new ArrayList<String>();
		
		for(Entry<String, PlayerLink> o : Storage.playerStorage.entrySet())
		{
			if(o.getKey().equalsIgnoreCase(ip))
			{
				found++;
				
				if(o.getValue()._onlineSince == 0)
				{
					output.add("\u00A7a" + o.getValue()._name + "");
				}
				else
				{
					output.add("\u00A7a" + o.getValue()._name + ": \u00A79" + o.getValue().returnOnline());
				}
			}
		}
		
		return new LookupData(output);
	}
	
}
