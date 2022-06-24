package de.catalysmrl.catagens.commands.subcommands;

import de.catalysmrl.catagens.CataGens;
import de.catalysmrl.catagens.commands.CataCommand;
import de.catalysmrl.catagens.gens.Generator;
import de.catalysmrl.catagens.gens.GensManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ListCommand implements CataCommand {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getPermission() {
        return "2gens.command.list";
    }

    @Override
    public String getUsage() {
        return "/gen list";
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append(CataGens.PREFIX).append("Registered generators:\n");
        for (String gen : GensManager.getInstance().getGensListNames()) {
            builder.append("§6").append(gen).append("§d, ");
        }
        sender.sendMessage(builder.substring(0, builder.length() - 2));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
