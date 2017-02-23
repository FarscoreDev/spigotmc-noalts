package net.Farscore.IPUtils.Storage;

import java.util.ArrayList;

public class BlacklistManager {
	
	public static boolean checkBlacklist(String ip)
	{
		return Configuration.isBlacklisted(ip);
	}
	
}
