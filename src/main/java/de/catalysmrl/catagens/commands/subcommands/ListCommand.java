package de.catalysmrl.catagens.commands.subcommands;

import de.catalysmrl.catagens.CataGens;
import de.catalysmrl.catagens.commands.CataCommand;
import de.catalysmrl.catagens.gens.GensManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ListCommand implements CataCommand {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getPermission() {
        return "catagens.command.list";
    }

    @Override
    public String getUsage() {
        return "/cg list";
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        sender.sendMessage(CataGens.PREFIX + "Registered generators:", GensManager.getInstance().getGensListNames().toString());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
