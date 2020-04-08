/*
 * Copyright (c) 2020 Sybsuper
 * All Rights Reserved
 *
 * Do not use this code without permission of the developer.
 */

package me.sybsuper.SybCommandBlocker;

import me.sybsuper.SybCommandBlocker.listeners.CommandListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public FileConfiguration config;

	@Override
	public void onEnable() {
		config = getConfig();
		config.options().copyDefaults(true);
		saveConfig();
		Bukkit.getPluginManager().registerEvents(new CommandListener(this), this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender.hasPermission("sybcommandblocker.reload")) {
			reloadConfig();
			config = getConfig();
			config.options().copyDefaults(true);
			saveConfig();
			sender.sendMessage("Successfully reloaded the config.yml file.");
		}
		return true;
	}
}
