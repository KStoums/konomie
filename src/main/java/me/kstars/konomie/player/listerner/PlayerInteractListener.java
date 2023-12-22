package me.kstars.konomie.player.listerner;

import me.kstars.konomie.item.ClickableItem;
import me.kstars.konomie.menu.ManagementMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!Objects.equals(event.getView().getOriginalTitle(), new ManagementMenu().getTitleString())) {
            return;
        }

        ManagementMenu menu = new ManagementMenu();
        for (ClickableItem item : menu.getItemsMenu()) {
            if (Objects.equals(event.getCurrentItem().getItemMeta().getDisplayName(), item.getItem().getItemMeta().getDisplayName())) {
                item.onClick((Player) event.getView().getPlayer(), event);
                return;
            }
        }
    }
}
