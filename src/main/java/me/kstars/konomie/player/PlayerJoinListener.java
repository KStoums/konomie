package me.kstars.konomie.player;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class PlayerJoinListener implements Listener {
    private static final Logger logger = Logger.getLogger(PlayerJoinListener.class.getName());

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
