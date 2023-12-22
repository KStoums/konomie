package me.kstars.konomie.item;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class CloseInventoryItem extends ClickableItem {
    @Override
    public Component getItemName() {
        return Component.text("Close menu", NamedTextColor.RED);
    }

    @Override
    public List<Component> getItemLore() {
        return Collections.emptyList();
    }

    @Override
    public Integer getItemSlot() {
        return 4;
    }

    @Override
    public Integer getItemQuantity() {
        return 1;
    }

    @Override
    public ItemStack getItem() {
        ItemStack closeItem = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = closeItem.getItemMeta();
        itemMeta.displayName(this.getItemName());
        itemMeta.lore(this.getItemLore());
        closeItem.setItemMeta(itemMeta);
        return closeItem;
    }

    @Override
    public void onClick(Player player, InventoryClickEvent event) {
        event.setCancelled(true);
        player.closeInventory();
    }
}
