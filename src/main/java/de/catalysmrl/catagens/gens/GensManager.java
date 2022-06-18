package de.catalysmrl.catagens.gens;

import de.catalysmrl.catagens.CataGens;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GensManager {

    private static GensManager gensManager;

    public static GensManager getInstance() {
        return gensManager;
    }

    private List<Generator> generators;

    public GensManager() {
        gensManager = this;
        generators = loadGensFromFiles();
        Bukkit.getScheduler().runTaskTimer(CataGens.getInstance(), gensManager::saveAllGens, 120, 600 * 20);
    }

    public List<Generator> getGenerators() {
        return generators;
    }

    public Generator getGenerator(String name) {
        for (Generator generator : generators) {
            if (generator.getName().equals(name)) {
                return generator;
            }
        }
        return null;
    }

    /**
     * Registers the generator and automatically starts it.
     * @param generator The generator you want to register
     * @return true if generator was not yet present
     */
    public boolean registerGenerator(Generator generator) {
        if (!containsGenerator(generator.getName())) {
            generators.add(generator);
            return true;
        }
        return false;
    }

    public boolean containsGenerator(String name) {
        for (Generator gen : generators) {
            if (gen.getName().equals(name)) return true;
        }
        return false;
    }

    private List<Generator> loadGensFromFiles() {
        List<Generator> gens = new ArrayList<>();

        File file = new File(CataGens.getInstance().getDataFolder() + "/generators");
        File[] files = file.listFiles(File::isFile);

        if (files == null) {
            return gens;
        }

        for (File value : files) {
            YamlConfiguration genFile = YamlConfiguration.loadConfiguration(value);
            if (genFile.contains("Generator") && genFile.get("Generator") != null) {
                Generator generator = (Generator) genFile.get("Generator");
                gens.add(generator);
                generator.start();
            }
        }

        return gens;
    }

    public List<String> getGensListNames() {
        List<String> gensListNames = new ArrayList<>();
        for (Generator gen : generators) {
            gensListNames.add(gen.getName());
        }
        return gensListNames;
    }

    public void saveAllGens() {
        generators.forEach(Generator::save);
    }
}
