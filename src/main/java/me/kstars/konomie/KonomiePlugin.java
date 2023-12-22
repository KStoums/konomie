package me.kstars.konomie;

import com.google.gson.Gson;
import me.kstars.konomie.command.CommandRegister;
import me.kstars.konomie.command.KonomieCommand;
import me.kstars.konomie.player.PlayerDataFileChecker;
import me.kstars.konomie.player.PlayerDataStorage;
import me.kstars.konomie.player.command.MoneyCommand;
import me.kstars.konomie.player.listerner.PlayerListenerRegister;
import org.apache.logging.log4j.util.Strings;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Logger;

public class KonomiePlugin extends JavaPlugin {
    private static final Logger logger = Logger.getLogger(KonomiePlugin.class.getName());
    private final Gson gson = new Gson();

    @Override
    public void onEnable() {
        logger.info("Konomie plugin enabling...");

        PlayerDataFileChecker playerDataFileChecker = new PlayerDataFileChecker();
        PlayerDataStorage playerDataStorage = new PlayerDataStorage(playerDataFileChecker, this.gson);

        CommandRegister commandRegister = new CommandRegister(this);
        KonomieCommand konomieCommand = new KonomieCommand(Strings.EMPTY);
        commandRegister.registerCommands(Arrays.asList(new MoneyCommand(playerDataStorage), konomieCommand));

        PlayerListenerRegister playerListenerRegister = new PlayerListenerRegister(this, playerDataStorage);
        playerListenerRegister.registerListeners();
    }

    @Override
    public void onDisable() {
        logger.info("Disabling Konimie plugin...");
    }
}