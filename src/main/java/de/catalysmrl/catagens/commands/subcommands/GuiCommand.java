package de.catalysmrl.catagens.commands.subcommands;

import de.catalysmrl.catagens.commands.CataCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GuiCommand implements CataCommand {
    @Override
    public String getName() {
        return "gui";
    }

    @Override
    public String getPermission() {
        return "catagens.command.gui";
    }

    @Override
    public String getUsage() {
        return "/gens gui";
    }

    @Override
    public boolean onlyPlayers() {
        return true;
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        Player player = (Player) sender;

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
