package me.kstars.konomie.player;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final PlayerDataStorage playerDataStorage;

    public PlayerQuitListener(PlayerDataStorage playerDataStorage) {
        this.playerDataStorage = playerDataStorage;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.quitMessage(Component.empty());
    }
}
