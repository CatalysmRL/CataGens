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

public class RenameCommand implements CataCommand {
    @Override
    public String getName() {
        return "rename";
    }

    @Override
    public String getPermission() {
        return "2gens.command.rename";
    }

    @Override
    public String getUsage() {
        return "/gen rename <gen> <name>";
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length != 4) {
            sender.sendMessage(CataGens.PREFIX + getUsage());
            return;
        }

        if (!GensManager.getInstance().containsGenerator(args[1])) {
            sender.sendMessage(CataGens.PREFIX + "§cGenerator is not registered!");
            return;
        }

        if (GensManager.getInstance().containsGenerator(args[2])) {
            sender.sendMessage(CataGens.PREFIX + "§cGenerator is already registered!");
            return;
        }

        Generator gen = GensManager.getInstance().getGenerator(args[1]);
        gen.setName(args[2]);
        gen.getFile().renameTo(new File(CataGens.getInstance().getDataFolder() + "/generators/" + args[2]));
        sender.sendMessage(CataGens.PREFIX + "Renamed generator to §6" + args[2]);
        gen.save();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], GensManager.getInstance().getGensListNames(), new ArrayList<>(GensManager.getInstance().getGensListNames().size()));
        }

        return Collections.emptyList();
    }
}
