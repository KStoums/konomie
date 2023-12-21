package me.kstars.konomie.command;

import org.bukkit.command.Command;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CommandRegister {
    private final JavaPlugin plugin;

    public CommandRegister(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerCommands(List<Command> commands) {
        commands.forEach(command -> this.plugin.getServer().getCommandMap().register(command.getName(), command));
    }
}
