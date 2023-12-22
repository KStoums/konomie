package me.kstars.konomie.player.listerner;

import me.kstars.konomie.player.PlayerDataStorage;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class PlayerListenerRegister {
    private final Plugin plugin;
    private final PlayerDataStorage playerDataStorage;

    public PlayerListenerRegister(Plugin plugin, PlayerDataStorage playerDataStorage) {
        this.plugin = plugin;
        this.playerDataStorage = playerDataStorage;
    }

    public void registerListeners() {
        Arrays.asList(
                new PlayerJoinListener(this.playerDataStorage),
                new PlayerQuitListener(),
                new PlayerInteractListener()
        ).forEach(listener -> plugin.getServer().getPluginManager().registerEvents(listener, plugin));
    }
}
