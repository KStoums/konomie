package me.kstars.konomie.data;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        File dataFile = new File("./resources/data/data.json");

        if (!dataFile.exists()) {
            try {
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
        String filter = "{'playerName' : '"+ playerName+"'}";
        PlayerData playerData = gson.create().fromJson(filter, PlayerData.class);

        return playerData;
    }
}
