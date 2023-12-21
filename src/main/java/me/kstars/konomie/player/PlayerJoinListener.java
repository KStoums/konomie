package me.kstars.konomie.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;
import java.util.UUID;

public class PlayerJoinListener implements Listener {
    private final PlayerDataStorage playerDataStorage;

    public PlayerJoinListener(PlayerDataStorage playerDataStorage) {
        this.playerDataStorage = playerDataStorage;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID playerUuid = event.getPlayer().getUniqueId();
        Optional<PlayerData> player = this.playerDataStorage.getPlayer(playerUuid);

        if (player.isPresent()) {
            return;
        }

        this.playerDataStorage.addPlayer(playerUuid, 0);
    }
}
