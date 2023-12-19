package me.kstars.konomie;

import com.google.gson.Gson;
import me.kstars.konomie.player.PlayerDataFileChecker;
import me.kstars.konomie.player.PlayerDataStorage;
import me.kstars.konomie.player.PlayerListenerRegister;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private final Gson gson = new Gson();

    @Override
    public void onEnable() {
        logger.info("Konomie plugin enabling...");
        File dataFile = new File("./plugins/Konomie/playerData.json");

        PlayerDataFileChecker playerDataFileChecker = new PlayerDataFileChecker();
        playerDataFileChecker.playerDataFileChecker(dataFile, this.gson);

        PlayerDataStorage playerDataStorage = new PlayerDataStorage(dataFile, this.gson);
        PlayerListenerRegister playerListenerRegister = new PlayerListenerRegister(this, playerDataStorage);
        playerListenerRegister.registerListeners();
    }

    @Override
    public void onDisable() {
        logger.info("Disabling Konimie plugin...");
    }
}