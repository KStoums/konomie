package me.kstars.konomie.player;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class PlayerJoinListener implements Listener {
    private static final Logger logger = Logger.getLogger(PlayerJoinListener.class.getName());

    private static final Component WELCOME_MESSAGE = Component.text(ChatColor.YELLOW + "Bienvenue à "
            + ChatColor.AQUA + "%s "
            + ChatColor.YELLOW + "sur ce magnifique serveur de bidouillage ! "
            + ChatColor.GRAY + ChatColor.ITALIC + "(%d)");
    private static final Component ERROR_PLAYER_DATA_IS_EMPTY = Component.text(ChatColor.RED + "Vous avez été exclu car vous n'avez pas de profil sur le serveur")
            .appendNewline()
            .append(Component.text(ChatColor.RED + "Votre profil a été créé, vous pouvez désormais vous reconnectez"));

    private final PlayerDataStorage playerDataStorage;

    public PlayerJoinListener(PlayerDataStorage playerDataStorage) {
        this.playerDataStorage = playerDataStorage;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        UUID playerUuid = event.getPlayer().getUniqueId();

        Optional<PlayerData> playerDataOptional = playerDataStorage.getPlayer(playerUuid);
        if (playerDataOptional.isEmpty()) {
            try {
                this.playerDataStorage.addPlayer(playerUuid, playerName, 0.0);
            } catch (IOException e) {
                logger.severe(String.format("Error when add player into playersData : %s", e.getMessage()));
                return;
            }
        }

        int onlinePlayers = Bukkit.getOnlinePlayers().size();
        String.format(WELCOME_MESSAGE.toString(), playerName, onlinePlayers);
        event.joinMessage(WELCOME_MESSAGE);
    }
}
