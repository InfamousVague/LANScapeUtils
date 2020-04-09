package com.github.retropronghorn.lanscapeutils.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class CommandInfo implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Plugin instance = sender.getServer().getPluginManager().getPlugin("LANScapeUtils");
        sender.sendMessage(
                ChatColor.GREEN + instance.getName() + " - " +
                instance.getDescription().getVersion() + " by " + instance.getDescription().getAuthors());
        return false;
    }
}
