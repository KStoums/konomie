package me.kstars.konomie.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.logging.log4j.util.Strings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class KonomieCommand extends Command {
    private static final Component INVENTORY_NAME = Component.text("Konomie", NamedTextColor.YELLOW);
    private static final String PERMISSION = "konimie.konimie.use";

    public KonomieCommand(@NotNull String name) {
        super("konomie", "Open the player management menu", Strings.EMPTY, Collections.emptyList());
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String cmd, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (!player.hasPermission(PERMISSION)) {
            player.sendMessage(Component.text("Error: You don't have access to this command.", NamedTextColor.RED));
            return false;
        }

        Inventory inventory = Bukkit.createInventory(null, 9, INVENTORY_NAME);

        ItemStack sign = this.createGuiItem(Material.OAK_SIGN,
                Component.text("Add money to player", NamedTextColor.DARK_GREEN),
                Collections.emptyList());

        ItemStack barrier = this.createGuiItem(Material.BARRIER,
                Component.text("Reset balance from player", NamedTextColor.RED),
                Collections.emptyList());

        Inventory finalInventory = this.setItemsToInventory(inventory, Map.of(sign, 2, barrier, 6));
        player.openInventory(finalInventory);
        return true;
    }

    public ItemStack createGuiItem(Material material, Component itemName, List<Component> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(itemName);
        itemMeta.lore(lore);

        item.setItemMeta(itemMeta);

        return item;
    }

    public Inventory setItemsToInventory(Inventory inventory, Map<ItemStack, Integer> itemStackMap) {
        itemStackMap.forEach((itemStack, integer) -> inventory.setItem(integer, itemStack));
        return inventory;
    }
}
