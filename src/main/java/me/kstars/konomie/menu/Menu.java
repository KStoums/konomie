package me.kstars.konomie.menu;

import me.kstars.konomie.item.ClickableItem;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public abstract class Menu {
    public abstract Component getTitle();

    public abstract String getTitleString();

    public abstract Inventory getInventory();

    public abstract int getRows();

    public abstract List<ClickableItem> getItemsMenu();

    public abstract InventoryHolder getOwner();
}
