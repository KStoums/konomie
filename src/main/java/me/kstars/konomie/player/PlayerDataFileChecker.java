package me.kstars.konomie.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;

public class PlayerDataFileChecker {
    private static final Logger logger = Logger.getLogger(PlayerDataFileChecker.class.getName());

    private static final Type PLAYER_LIST_TYPE = new TypeToken<List<PlayerData>>() {}.getType();
    public void playerDataFileChecker(File dataFile, Gson gson) {
        if (!dataFile.exists()) {
            try {
                dataFile.getParentFile().mkdirs();
                Files.createFile(dataFile.toPath());

                FileWriter fileWriter = new FileWriter(dataFile);
                String defaultJsonList = gson.toJson(PLAYER_LIST_TYPE);
                fileWriter.write(defaultJsonList);
            } catch (IOException e) {
                logger.severe(String.format("Error when create playerData file : %s", e.getMessage()));
            }
        }
    }
}
