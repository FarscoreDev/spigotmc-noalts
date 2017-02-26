package net.Farscore.IPUtils.BungeeCord;

import net.Farscore.IPUtils.BungeeCord.Command.IPUtils;
import net.md_5.bungee.api.plugin.Plugin;

public class Core extends Plugin {
	
	public static Plugin plugin = null;
	public static BungeeConfig config = null;
	
	public void onEnable()
	{
		getProxy().getPluginManager().registerCommand(this, new IPUtils());
		getProxy().getPluginManager().registerListener(this, new Events());
		plugin = this;
		config = new BungeeConfig(this);
	}
	
	public void onDisable()
	{
		plugin = null;
	}
	
}
