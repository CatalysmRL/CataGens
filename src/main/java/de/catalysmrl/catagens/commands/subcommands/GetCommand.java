package de.catalysmrl.catagens.commands.subcommands;

import de.catalysmrl.catagens.CataGens;
import de.catalysmrl.catagens.commands.CataCommand;
import de.catalysmrl.catagens.gens.Generator;
import de.catalysmrl.catagens.gens.GensManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetCommand implements CataCommand {
    @Override
    public String getName() {
        return "get";
    }

    @Override
    public List<String> getAliases() {
        return List.of("give");
    }

    @Override
    public String getPermission() {
        return "catagens.command.get";
    }

    @Override
    public String getUsage() {
        return "/gen get <gen>";
    }

    @Override
    public boolean onlyPlayers() {
        return true;
    }

    @Override
    public void onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(CataGens.PREFIX + getUsage());
            return;
        }

        if (!GensManager.getInstance().containsGenerator(args[1])) {
            sender.sendMessage(CataGens.PREFIX + "§cGenerator is not registered!");
            return;
        }

        Generator gen = GensManager.getInstance().getGenerator(args[1]);
        if (gen.getGenItem() == null) {
            sender.sendMessage(CataGens.PREFIX + "§cThis generator has no gen item set");
            return;
        }

        Player player = (Player) sender;
        player.getInventory().addItem(gen.getGenItem().clone());
        player.sendMessage(CataGens.PREFIX + "§aGenerator has been added to your inventory");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], GensManager.getInstance().getGensListNames(), new ArrayList<>(GensManager.getInstance().getGensListNames().size()));
        }

        return Collections.emptyList();
    }
}
