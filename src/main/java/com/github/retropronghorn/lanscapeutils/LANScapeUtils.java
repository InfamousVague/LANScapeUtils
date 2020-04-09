package com.github.retropronghorn.lanscapeutils;

import com.github.retropronghorn.lanscapeutils.Commands.Commands;
import com.github.retropronghorn.lanscapeutils.NewPlayer.NewPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LANScapeUtils extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);

        // Commands
        Commands.registerCommands(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * On player join, run checks to see if is is a new player.
     * NewPlayer will handle events if this is the case.
     *
     * @param event org.bukkit.event.player.PlayerJoinEvent
     */
    @EventHandler
    public void onJoinServer(PlayerJoinEvent event) {
        NewPlayer.runCheck(
                event.getPlayer(),
                Objects.requireNonNull(
                        this.getConfig().getConfigurationSection("language.new_player")
                ),
                this.getConfig()
        );
    }
}
