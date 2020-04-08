/*
 * Copyright (c) 2020 Sybsuper
 * All Rights Reserved
 *
 * Do not use this code without permission of the developer.
 */

package me.sybsuper.SybCommandBlocker.listeners;

import me.sybsuper.SybCommandBlocker.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
	private Main plugin;

	public CommandListener(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		if (!plugin.config.getBoolean("enableBypassPermission") || !e.getPlayer().hasPermission("sybcommandblocker.bypass")) {
			for (String command : plugin.config.getStringList("blocked.startWith")) {
				if (e.getMessage().toLowerCase().startsWith("/" + command.toLowerCase())) {
					e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("message")));
					e.setCancelled(true);
					break;
				}
			}
			for (String command : plugin.config.getStringList("blocked.contain")) {
				if (e.getMessage().toLowerCase().contains(command.toLowerCase())) {
					e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("message")));
					e.setCancelled(true);
					break;
				}
			}
			for (String command : plugin.config.getStringList("blocked.equal")) {
				if (e.getMessage().equalsIgnoreCase("/" + command)) {
					e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("message")));
					e.setCancelled(true);
					break;
				}
			}
		}
	}
}
