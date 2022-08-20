package de.catalysmrl.catagens.commands.subcommands;

import de.catalysmrl.catagens.CataGens;
import de.catalysmrl.catagens.commands.CataCommand;
import de.catalysmrl.catagens.gens.Generator;
import de.catalysmrl.catagens.gens.GensManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DelayCommand implements CataCommand {
    @Override
    public String getName() {
        return "timing";
    }

    @Override
    public String getPermission() {
        return "catagens.command.timing";
    }

    @Override
    public String getUsage() {
        return "/gen timing <mine> <time> <unit>";
    }

    @Override
    public List<String> getAliases() {
        return List.of("delay");
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

        Generator generator = GensManager.getInstance().getGenerator(args[1]);
        int multiplier = 0;
        switch (args[3].toLowerCase()) {
            case "ticks", "t" -> multiplier = 1;
            case "seconds", "s" -> multiplier = 20;
            case "minutes", "m" -> multiplier = 20 * 60;
            case "hours", "h" -> multiplier = 20 * 60 * 60;
            default -> {
                sender.sendMessage(CataGens.PREFIX + "Please use §6ticks§7, §6seconds§7, §6minutes §7or §6hours");
                return;
            }
        }
        generator.setDelay(Long.parseLong(args[2]) * multiplier);
        generator.start();

        sender.sendMessage(CataGens.PREFIX + "§aSuccessfully set delay of §6" + args[1] + " §ato §b" + args[2] + " §7" + args[3]);
        generator.save();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], GensManager.getInstance().getGensListNames(), new ArrayList<>(GensManager.getInstance().getGensListNames().size()));
        } else if (args.length == 4) {
            return StringUtil.copyPartialMatches(args[3], List.of("seconds", "ticks", "minutes", "hours"), new ArrayList<>());
        }

        return Collections.emptyList();
    }
}
