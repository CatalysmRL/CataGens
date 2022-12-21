package de.catalysmrl.catagens.api.menu;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private final Player player;

    public PlayerMenuUtility(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
