package me.kstars.konomie;

import com.google.gson.Gson;
import me.kstars.konomie.player.PlayerDataFileChecker;
import me.kstars.konomie.player.PlayerDataStorage;
import me.kstars.konomie.player.PlayerListenerRegister;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Konomie extends JavaPlugin {
    private static final Logger logger = Logger.getLogger(Konomie.class.getName());
    private final Gson gson = new Gson();

    @Override
    public void onEnable() {
        logger.info("Konomie plugin enabling...");

        PlayerDataFileChecker playerDataFileChecker = new PlayerDataFileChecker();
        PlayerDataStorage playerDataStorage = new PlayerDataStorage(playerDataFileChecker, this.gson);
        PlayerListenerRegister playerListenerRegister = new PlayerListenerRegister(this, playerDataStorage);
        playerListenerRegister.registerListeners();
    }

    @Override
    public void onDisable() {
        logger.info("Disabling Konimie plugin...");
    }
}