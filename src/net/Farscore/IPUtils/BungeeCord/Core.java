package net.Farscore.IPUtils.BungeeCord;

import net.md_5.bungee.api.plugin.Plugin;

public class Core extends Plugin {
	
	public static Plugin plugin = null;
	public static BungeeConfig config = null;
	
	public void onEnable()
	{
		plugin = this;
		config = new BungeeConfig(this);
	}
	
	public void onDisable()
	{
		plugin = null;
	}
	
}
