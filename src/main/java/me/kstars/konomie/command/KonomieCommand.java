package me.kstars.konomie.command;

import me.kstars.konomie.menu.ManagementMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class KonomieCommand extends Command {
    public static final Component INVENTORY_NAME = Component.text("Konomie", NamedTextColor.YELLOW);
    private static final String PERMISSION = "konimie.konimie.use";
    public final String BARRIER_ITEM_NAME = "Reset balance from player";
    public final String SIGN_ITEM_NAME = "Add money to player";

    public KonomieCommand(@NotNull String name) {
        super("konomie", "Open the player management menu", "§cSyntax: /konomie <player>", Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String cmd, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (!player.hasPermission(PERMISSION)) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            player.sendMessage(Component.text("Error: You don't have access to this command.", NamedTextColor.RED));
            return false;
        }

        if (args.length != 1) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            player.sendMessage(Component.text("Syntax: /konomie <player>", NamedTextColor.RED));
            return false;
        }

        Player victim = Bukkit.getPlayer(args[0]);
        if (!victim.isOnline()) {
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            player.sendMessage(Component.text(String.format("Error: %s is not online.", victim.getName()), NamedTextColor.RED));
            return false;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
        ManagementMenu menu = new ManagementMenu();
        player.openInventory(menu.getInventory());
        return true;
    }
}
