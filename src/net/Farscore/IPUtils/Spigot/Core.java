package net.Farscore.IPUtils.Spigot;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
	
	public static Plugin plugin = null;
	
	public void onEnable()
	{
		plugin = this;
	}
	
	public void onDisable()
	{
		plugin = null;
	}
	
}
