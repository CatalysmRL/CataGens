package de.catalysmrl.catagens.commands.subcommands;

import de.catalysmrl.catagens.CataGens;
import de.catalysmrl.catagens.commands.CataCommand;
import de.catalysmrl.catagens.gens.Generator;
import de.catalysmrl.catagens.gens.GensManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteCommand implements CataCommand {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getPermission() {
        return "catagens.command.delete";
    }

    @Override
    public String getUsage() {
        return "/gen delete <gen>";
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(CataGens.PREFIX + getUsage());
            return;
        }

        if (!GensManager.getInstance().containsGenerator(args[1])) {
            sender.sendMessage(CataGens.PREFIX + "§cGen is not registered!");
            return;
        }

        Generator generator = GensManager.getInstance().getGenerator(args[1]);
        GensManager.getInstance().deleteGenerator(generator);

        sender.sendMessage(CataGens.PREFIX + "§aSuccessfully deleted §6" + args[1]);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], GensManager.getInstance().getGensListNames(), new ArrayList<>(GensManager.getInstance().getGensListNames().size()));
        }

        return Collections.emptyList();
    }
}
