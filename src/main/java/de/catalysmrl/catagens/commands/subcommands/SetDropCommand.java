package de.catalysmrl.catagens.commands.subcommands;

import de.catalysmrl.catagens.CataGens;
import de.catalysmrl.catagens.commands.CataCommand;
import de.catalysmrl.catagens.gens.Generator;
import de.catalysmrl.catagens.gens.GensManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetDropCommand implements CataCommand {
    @Override
    public String getName() {
        return "setdrop";
    }

    @Override
    public String getPermission() {
        return "catagens.command.setdrop";
    }

    @Override
    public String getUsage() {
        return "/cg setdrop <gen>";
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
            sender.sendMessage(CataGens.PREFIX + "§cGen is not registered!");
            return;
        }

        Player player = (Player) sender;

        Generator generator = GensManager.getInstance().getGenerator(args[1]);
        generator.setDropItem(player.getInventory().getItemInMainHand().clone());
        sender.sendMessage(CataGens.PREFIX + "§aSet drop item for §6" + args[1]);
        generator.save();

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
