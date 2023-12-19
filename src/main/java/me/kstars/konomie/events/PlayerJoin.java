package me.kstars.konomie.events;

import me.kstars.konomie.data.Data;
import me.kstars.konomie.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        Data.PlayerData playerData = Data.getPlayer(playerName);

        if (playerData == null) {
            Data.addPlayer(playerName);
        }

        event.setJoinMessage("");
        Bukkit.broadcastMessage(String.format(Messages.WELCOME_MESSAGE, playerName, Bukkit.getOnlinePlayers().size()));
    }
}
