package de.catalysmrl.catagens.commands.subcommands;

import de.catalysmrl.catagens.CataGens;
import de.catalysmrl.catagens.commands.CataCommand;
import de.catalysmrl.catagens.gens.Generator;
import de.catalysmrl.catagens.gens.GensManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class DelayCommand implements CataCommand {
    @Override
    public String getName() {
        return "delay";
    }

    @Override
    public String getPermission() {
        return "catagens.command.delay";
    }

    @Override
    public String getUsage() {
        return "/cm delay <mine> <ticks>";
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length != 3) {
            sender.sendMessage(CataGens.PREFIX + getUsage());
            return;
        }

        if (!GensManager.getInstance().containsGenerator(args[1])) {
            sender.sendMessage(CataGens.PREFIX + "§cGenerator is not registered!");
            return;
        }

        Generator generator = GensManager.getInstance().getGenerator(args[1]);
        generator.setDelay(Long.parseLong(args[2]));
        generator.start();

        sender.sendMessage(CataGens.PREFIX + "§aSuccessfully set delay of §6" + args[1] + " §ato §b" + args[2] + " §7ticks.");
        generator.save();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
