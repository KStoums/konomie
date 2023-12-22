package me.kstars.konomie.menu;

import me.kstars.konomie.item.ClickableItem;
import me.kstars.konomie.item.CloseInventoryItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public class ManagementMenu extends Menu {
    @Override
    public Component getTitle() {
        return Component.text(this.getTitleString());
    }

    @Override
    public String getTitleString() {
        return ChatColor.YELLOW + "Konomie";
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this.getOwner(), this.getRows(), this.getTitle());
        this.getItemsMenu().forEach(clickableItem -> inventory.setItem(clickableItem.getItemSlot(), clickableItem.getItem()));
        return inventory;
    }

    @Override
    public int getRows() {
        return 9;
    }

    @Override
    public List<ClickableItem> getItemsMenu() {
        return List.of(new CloseInventoryItem());
    }

    @Override
    public InventoryHolder getOwner() {
        return null;
    }
}
