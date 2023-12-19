package me.kstars.konomie.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PlayerDataStorage {
    private static final Logger logger = Logger.getLogger(PlayerDataStorage.class.getName());
    private static final Type PLAYER_LIST_TYPE = new TypeToken<List<PlayerData>>() {}.getType();

    private final File dataFile;
    private final Gson gson;


    public PlayerDataStorage(File dataFile, Gson gson) {
        this.dataFile = dataFile;
        this.gson = gson;
    }

    public void addPlayer(UUID playerUuid, String playerName, double pay) throws IOException {
        try (FileWriter fileWriter = new FileWriter(this.dataFile)) {
            this.gson.toJson(this.getPlayers().add(new PlayerData(playerUuid, playerName, pay)), fileWriter);
        }
    }

    public Optional<PlayerData> getPlayer(UUID playerUuid) {
        List<PlayerData> playersData = this.getPlayers();

         if (playersData != null) {
                return playersData.stream().filter(playerData -> playerData.getUuid().equals(playerUuid))
                        .findFirst();
            }
         return Optional.empty();
    }

    public List<PlayerData> getPlayers() {
        try (FileReader fileReader = new FileReader(this.dataFile)) {
            List<PlayerData> playersData = this.gson.fromJson(fileReader, PLAYER_LIST_TYPE);
            if (playersData != null) {
                return this.gson.fromJson(fileReader, PLAYER_LIST_TYPE);
            }
            return Collections.emptyList();
        } catch (IOException e) {
            logger.severe(String.format("Error when get players from playersData : %s", e.getMessage()));
            return Collections.emptyList();
        }
    }

    public void deletePlayer(UUID playerUuid) {
        try (FileReader fileReader = new FileReader(this.dataFile)) {
            List<PlayerData> playersData = this.getPlayers().stream().filter(playerData -> playerData.getUuid().equals(playerUuid))
                    .collect(Collectors.toList());

            FileWriter fileWriter = new FileWriter(this.dataFile);
            this.gson.toJson(playersData, fileWriter);
        } catch (IOException e) {
            logger.severe(String.format("Error when delete player from playersData : %s", e.getMessage()));
        }
    }
}
