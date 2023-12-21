package me.kstars.konomie.player.listerner;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    public PlayerQuitListener() {}

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
    }
}
