package me.kstars.konomie.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandRegister {
    private final JavaPlugin plugin;

    public CommandRegister(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerCommand(CommandExecutor... commandExecutors) {
        for (CommandExecutor commandExecutor : commandExecutors) {
            String className = commandExecutor.getClass().getName().toLowerCase();
            String[] classNameSplitted = className.split("\\.");
            this.plugin.getCommand(classNameSplitted[classNameSplitted.length-1]).setExecutor(commandExecutor);
        }
    }
}
