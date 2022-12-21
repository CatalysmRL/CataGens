package de.catalysmrl.catagens.api.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;

public abstract class PaginatedMenu extends Menu {

    protected int page = 0;
    protected int itemsPerPage = 28;
    protected int index;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    public void addMenuBorder() {

        inventory.setItem(48, makeItem(Material.DARK_OAK_BUTTON, Component.text("Previous Page").color(NamedTextColor.GOLD)));
        inventory.setItem(49, makeItem(Material.BARRIER, Component.text("Close").color(NamedTextColor.RED)));
        inventory.setItem(50, makeItem(Material.DARK_OAK_BUTTON, Component.text("Next Page").color(NamedTextColor.GOLD)));

        for (int i = 0; i < 10; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_ITEM);
            }
        }

        inventory.setItem(17, super.FILLER_ITEM);
        inventory.setItem(18, super.FILLER_ITEM);
        inventory.setItem(26, super.FILLER_ITEM);
        inventory.setItem(27, super.FILLER_ITEM);
        inventory.setItem(35, super.FILLER_ITEM);
        inventory.setItem(36, super.FILLER_ITEM);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_ITEM);
            }
        }
    }
}
