package me.kstars.konomie.player;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class PlayerDataStorage {
    private String PLAYERS_DATA_ROOT_PATH = "./plugins/Konomie/playersData/%s.json";
    private final Logger logger = Logger.getLogger(PlayerDataStorage.class.getName());
    private final PlayerDataFileChecker playerDataFileChecker;
    private final Gson gson;


    public PlayerDataStorage(PlayerDataFileChecker playerDataFileChecker, Gson gson) {
        this.playerDataFileChecker = playerDataFileChecker;
        this.gson = gson;
    }

    public void addPlayer(UUID playerUuid, double pay) {
        File playerDataFile = this.playerDataFileChecker.checkDataFile(String.format(PLAYERS_DATA_ROOT_PATH, playerUuid));
        try (FileWriter fileWriter = new FileWriter(playerDataFile)) {
            Optional<PlayerData> player = this.getPlayer(playerUuid);

            if (player.isPresent()) {
                return;
            }

            this.gson.toJson(new PlayerData(playerUuid, pay), fileWriter);
        } catch (IOException e) {
            logger.severe(String.format("Error when add player into playersData : %s", e.getMessage()));
        }
    }

    public Optional<PlayerData> getPlayer(UUID playerUuid) {
        File playerDatFile = new File(String.format(PLAYERS_DATA_ROOT_PATH, playerUuid));
        if (!playerDatFile.exists()) {
            return Optional.empty();
        }

        try (FileReader fileReader = new FileReader(String.format(PLAYERS_DATA_ROOT_PATH, playerUuid))) {
            PlayerData playerData = this.gson.fromJson(fileReader, PlayerData.class);
            return Optional.ofNullable(playerData);
        } catch (IOException e) {
            logger.severe(String.format("Error when get player into playersData : %s", e.getMessage()));
            return Optional.empty();
        }
    }

    public void deletePlayer(UUID playerUuid) {
        try (FileReader fileReader = new FileReader(String.format(PLAYERS_DATA_ROOT_PATH, playerUuid))) {
            Optional<PlayerData> player = this.getPlayer(playerUuid);
            if (player.isEmpty()) {
                return;
            }

            player.get().setPay(0);

            FileWriter fileWriter = new FileWriter(String.format(PLAYERS_DATA_ROOT_PATH, playerUuid));
            this.gson.toJson(player, fileWriter);
        } catch (IOException e) {
            logger.severe(String.format("Error when delete player : %s", e.getMessage()));
        }
    }
}
