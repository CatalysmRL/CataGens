package de.catalysmrl.catagens.commands;

import de.catalysmrl.catagens.CataGens;
import de.catalysmrl.catagens.commands.subcommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandsManager implements TabExecutor {

    private final ArrayList<CataCommand> registeredCommands = new ArrayList<>();

    public CommandsManager() {
        registeredCommands.add(new CreateCommand());
        registeredCommands.add(new ListCommand());
        registeredCommands.add(new SetGenCommand());
        registeredCommands.add(new SetDropCommand());
        registeredCommands.add(new DelayCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(CataGens.PREFIX + "Available commands:", "create");
            return true;
        }

        String cmdName = args[0].toLowerCase();
        if (!containsSubCommand(cmdName)) {
            sender.sendMessage(CataGens.PREFIX + "/cg");
            return true;
        }

        CataCommand cataCommand = getSubCommand(cmdName);
        if (!sender.hasPermission(cataCommand.getPermission())) {
            sender.sendMessage(CataGens.PREFIX + "§cYou do not have permissions to execute this command!");
            return true;
        }

        if (cataCommand.onlyPlayers() && !(sender instanceof Player)) {
            sender.sendMessage(CataGens.PREFIX + "§cOnly players may execute this command!");
            return true;
        }

        cataCommand.onCommand(sender, command, label, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }

    public boolean containsSubCommand(String cmdName) {
        for (CataCommand cataCommand : registeredCommands) {
            if (cataCommand.getName().equalsIgnoreCase(cmdName) || cataCommand.getAliases().contains(cmdName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public CataCommand getSubCommand(String cmdName) {

        for (CataCommand cataCommand : registeredCommands) {
            if (cataCommand.getName().equalsIgnoreCase(cmdName) || cataCommand.getAliases().contains(cmdName.toLowerCase())) {
                return cataCommand;
            }
        }
        return null;
    }
}
