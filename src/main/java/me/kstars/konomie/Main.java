package me.kstars.konomie;

import me.kstars.konomie.data.Data;
import me.kstars.konomie.events.Events;
import me.kstars.konomie.events.PlayerJoin;
import me.kstars.konomie.events.PlayerQuit;
import me.kstars.konomie.messages.Messages;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    Logger logger = Logger.getLogger("Konomie");

    @Override
    public void onEnable() {
        logger.log(Level.INFO, Messages.ENABLING_PLUGIN);
        Events.registerEvents(this, new PlayerJoin(), new PlayerQuit());

        Data.checkDataFile(logger);
    }

    @Override
    public void onDisable() {
        logger.log(Level.INFO, Messages.DISABLING_PLUGIN);
    }
}