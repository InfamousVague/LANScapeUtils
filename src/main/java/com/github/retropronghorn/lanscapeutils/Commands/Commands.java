package com.github.retropronghorn.lanscapeutils.Commands;

import com.github.retropronghorn.lanscapeutils.LANScapeUtils;

import java.util.Objects;

public class Commands {

    public static void registerCommands(LANScapeUtils instance) {
        Objects.requireNonNull(instance.getCommand("ls")).setExecutor(new CommandInfo());
    }
}
