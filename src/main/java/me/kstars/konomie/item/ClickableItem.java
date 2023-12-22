package me.kstars.konomie.item;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class ClickableItem {
    public abstract Component getItemName();

    public abstract List<Component> getItemLore();

    public abstract Integer getItemSlot();

    public abstract Integer getItemQuantity();

    public abstract ItemStack getItem();

    public abstract void onClick(Player player, InventoryClickEvent event);
}
