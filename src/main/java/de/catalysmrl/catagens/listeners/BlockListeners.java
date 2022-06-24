package de.catalysmrl.catagens.listeners;

import de.catalysmrl.catagens.CataGens;
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
                if (gen.getLocationList().contains(event.getBlock().getLocation())) {
                    gen.getLocationList().remove(event.getBlock().getLocation());
                    event.getPlayer().sendMessage(CataGens.PREFIX + "§cRemoved §6" + gen.getName() + "§c generator");
                    gen.save();
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        for (Generator gen : GensManager.getInstance().getGenerators()) {
            if (gen.getGenItem() != null && gen.getGenItem().isSimilar(event.getItemInHand())) {
                gen.addLocation(event.getBlock().getLocation());
                event.getPlayer().sendMessage(CataGens.PREFIX + "§aSet §6" + gen.getName() + "§a generator");
                gen.save();
                return;
            }
        }
    }
}
