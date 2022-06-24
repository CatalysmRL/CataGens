package de.catalysmrl.catagens;

import de.catalysmrl.catagens.commands.CommandsManager;
import de.catalysmrl.catagens.gens.Generator;
import de.catalysmrl.catagens.gens.GensManager;
import de.catalysmrl.catagens.listeners.BlockListeners;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CataGens extends JavaPlugin {

    private static CataGens instance;

    public static CataGens getInstance() {
        return instance;
    }

    private GensManager gensManager;

    public static String PREFIX = "§7[§32§bGens§7] §d";

    @Override
    public void onEnable() {
        instance = this;
        ConfigurationSerialization.registerClass(Generator.class);

        getConfig().options().copyDefaults(true);
        saveConfig();
        File gensDir = new File(getDataFolder() + "/generators");
        if (!gensDir.exists()) {
            gensDir.mkdirs();
        }

        gensManager = new GensManager();
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        gensManager.saveAllGens();
        instance = null;
        gensManager = null;
    }

    private void registerCommands() {
        getCommand("gen").setExecutor(new CommandsManager());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new BlockListeners(), this);
    }
}
