package me.kstars.konomie.command;

import me.kstars.konomie.player.PlayerData;
import me.kstars.konomie.player.PlayerDataStorage;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class Money implements CommandExecutor {
    private final PlayerDataStorage playerDataStorage;

    public Money(PlayerDataStorage playerDataStorage) {
        this.playerDataStorage = playerDataStorage;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        System.out.println("OK");
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        System.out.println(args);
        if (args.length != 0) {
            player.sendMessage(Component.text(Color.RED + "Syntax: /money"));
            return false;
        }

        Optional<PlayerData> playerData = this.playerDataStorage.getPlayer(player.getUniqueId());
        if (playerData.isEmpty()) {
            player.kick(Component.text(Color.RED + "Vous avez été exclu car vos données ne sont pas accessible"));
            return false;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_PIG_HURT, 1.0f, 1.0f);
        player.sendMessage(Component.text(ChatColor.YELLOW + "===================================")
                .appendNewline()
                .append(Component.text(ChatColor.YELLOW + "Votre solde : " + ChatColor.AQUA + playerData.get().getPay()))
                .appendNewline()
                .append(Component.text(ChatColor.YELLOW + "===================================")));
        return true;
    }
}
