package de.catalysmrl.catagens.listeners;

import de.catalysmrl.catagens.gens.Generator;
import de.catalysmrl.catagens.gens.GensManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockListeners implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        for (Generator gen : GensManager.getInstance().getGenerators()) {
            if (gen.getGenItem() != null && gen.getGenItem().getType().equals(event.getBlock().getType())) {
                gen.getLocationList().remove(event.getBlock().getLocation());
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        for (Generator gen : GensManager.getInstance().getGenerators()) {
            if (gen.getGenItem() != null && gen.getGenItem().isSimilar(event.getItemInHand())) {
                event.getPlayer().sendMessage("test");
                gen.addLocation(event.getBlock().getLocation());
                gen.save();
                return;
            }
        }
    }
}
