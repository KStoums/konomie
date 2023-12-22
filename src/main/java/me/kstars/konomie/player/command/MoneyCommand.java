package me.kstars.konomie.player.command;

import me.kstars.konomie.player.PlayerData;
import me.kstars.konomie.player.PlayerDataStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.logging.log4j.util.Strings;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Optional;

public class MoneyCommand extends Command {
    private final PlayerDataStorage playerDataStorage;

    public MoneyCommand(PlayerDataStorage playerDataStorage) {
        super("money", "Show your balance", Strings.EMPTY, Collections.emptyList());
        this.playerDataStorage = playerDataStorage;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String cmd, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (args.length != 0) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            player.sendMessage(Component.text("Syntax: /money", NamedTextColor.RED));
            return false;
        }

        Optional<PlayerData> playerData = this.playerDataStorage.getPlayer(player.getUniqueId());
        if (playerData.isEmpty()) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            player.kick(Component.text("You have been excluded because your data is not accessible", NamedTextColor.RED));
            return false;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
        player.sendMessage(Component.text("===================================", NamedTextColor.YELLOW)
                .appendNewline()
                .append(Component.text("Your balance : ", NamedTextColor.YELLOW))
                .append(Component.text(playerData.get().getPay() + "$", NamedTextColor.AQUA))
                .appendNewline()
                .append(Component.text("===================================", NamedTextColor.YELLOW)));
        return true;
    }
}
