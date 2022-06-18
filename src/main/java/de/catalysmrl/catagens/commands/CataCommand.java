package de.catalysmrl.catagens.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public interface CataCommand {

    String getName();

    default List<String> getAliases() {
        return Collections.emptyList();
    }

    String getPermission();

    String getUsage();

    default boolean onlyPlayers() {
        return false;
    }

    void onCommand(CommandSender sender, Command command, String commandLabel, String[] args);

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args);
}
