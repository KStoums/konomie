package me.kstars.konomie.data;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data {
    public static class PlayerData {
        public String playerName;
        public int pay;
    }

    static FileWriter dataFileWriter;
    static FileReader dataFileReader;
    static GsonBuilder gson;
    static Logger logger;

    public static void checkDataFile(Logger loggerr) {
        logger = loggerr;
        File dataFile = new File("./plugins/Konomie/data.json");

        if (!dataFile.exists()) {
            try {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Error when create json data file : " + e.getMessage());
                return;
            }
        }

        try {
            dataFileWriter = new FileWriter(dataFile.getPath());
            dataFileReader = new FileReader(dataFile.getPath());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error when initialise data file reader and writer : " + e.getMessage());
            return;
        }

        gson = new GsonBuilder();
    }

    public static void addPlayer(String playerName) {
        PlayerData playerData = new PlayerData();
        playerData.playerName = playerName;

        gson.create().toJson(playerData, dataFileWriter);
    }

    public static PlayerData getPlayer(String playerName) {
        Type playerListType = new TypeToken<List<PlayerData>>() {}.getType();
        List<PlayerData> playersData = gson.create().fromJson(dataFileReader, playerListType);

        if (playersData == null) {
            return null;
        }

        return searchPlayerIntoPlayersData(playersData, playerName);
    }

    private static PlayerData searchPlayerIntoPlayersData(List<PlayerData> playersData, String playerName) {
       return playersData.stream().filter(playerData -> playerData.playerName.equals(playerName))
                .findFirst()
                .orElse(null);
    }
}
