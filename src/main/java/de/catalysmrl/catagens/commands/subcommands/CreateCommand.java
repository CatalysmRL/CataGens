package de.catalysmrl.catagens.commands.subcommands;

import de.catalysmrl.catagens.CataGens;
import de.catalysmrl.catagens.commands.CataCommand;
import de.catalysmrl.catagens.gens.Generator;
import de.catalysmrl.catagens.gens.GensManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class CreateCommand implements CataCommand {
    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getPermission() {
        return "catagens.command.create";
    }

    @Override
    public String getUsage() {
        return "/gen create <gen>";
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(CataGens.PREFIX + getUsage());
            return;
        }

        Generator generator = new Generator(args[1]);
        boolean success = GensManager.getInstance().registerGenerator(generator);

        if (!success) {
            sender.sendMessage(CataGens.PREFIX + "§6" + args[1] + " §cis already registered");
            return;
        }

        sender.sendMessage(CataGens.PREFIX + "§aGenerator was successfully registered");
        generator.start();
        generator.save();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return Collections.emptyList();
    }
}
