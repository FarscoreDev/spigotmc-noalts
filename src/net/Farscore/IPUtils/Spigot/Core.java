package net.Farscore.IPUtils.Spigot;

import net.Farscore.IPUtils.Spigot.Command.IPUtils;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
	
	public static Plugin plugin = null;
	
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(new Events(), this);
		getCommand("iputils").setExecutor(new IPUtils());
		plugin = this;
	}
	
	public void onDisable()
	{
		plugin = null;
	}
	
}
