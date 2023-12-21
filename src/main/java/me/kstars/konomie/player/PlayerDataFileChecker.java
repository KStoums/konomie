package me.kstars.konomie.player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

public class PlayerDataFileChecker {
    private final Logger logger = Logger.getLogger(PlayerDataFileChecker.class.getName());

    public File checkDataFile(String playerDataFilePath) {
        File playerDataFile = new File(playerDataFilePath);
        if (!playerDataFile.exists()) {
            try {
                this.initDataFile(playerDataFile);
            } catch (IOException e) {
                logger.severe(String.format("Error when create playerData file : %s", e.getMessage()));
            }
        }

        return playerDataFile;
    }

    public void initDataFile(File playerDataFile) throws IOException {
        playerDataFile.getParentFile().mkdirs();
        Files.createFile(playerDataFile.toPath());
    }
}
