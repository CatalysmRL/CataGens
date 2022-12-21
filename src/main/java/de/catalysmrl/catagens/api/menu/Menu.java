package de.catalysmrl.catagens.api.menu;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Collectors;

public abstract class Menu implements InventoryHolder {

    protected PlayerMenuUtility playerMenuUtility;
    protected Player player;
    protected ItemStack FILLER_ITEM = makeItem(Material.GRAY_STAINED_GLASS_PANE, Component.text(" "));

    protected Inventory inventory;

    public Menu(PlayerMenuUtility playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
        this.player = playerMenuUtility.getPlayer();
    }

    public abstract Component getTitle();
    public abstract int getSize();

    public void open() {
        inventory = Bukkit.createInventory(this, getSize(), getTitle());

        setMenuItems();

        player.openInventory(inventory);
    }

    abstract void setMenuItems();

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public ItemStack makeItem(Material material, Component displayName, Component... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(displayName);
        meta.lore(Arrays.stream(lore).collect(Collectors.toList()));

        item.setItemMeta(meta);
        return item;
    }
    public ItemStack makeItem(Material material, int amount, Component displayName, Component... lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(displayName);
        meta.lore(Arrays.stream(lore).collect(Collectors.toList()));

        item.setItemMeta(meta);
        return item;
    }
}
