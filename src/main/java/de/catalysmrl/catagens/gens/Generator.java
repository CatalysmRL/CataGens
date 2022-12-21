package de.catalysmrl.catagens.gens;

import de.catalysmrl.catagens.CataGens;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class Generator implements Runnable, ConfigurationSerializable {

    private String name;
    private ItemStack genItem;
    private long delay = 60;
    private ItemStack dropItem;
    private ArrayList<Location> locationList = new ArrayList<>();

    private File file;
    private FileConfiguration configuration;

    boolean isRunning;
    BukkitTask task;

    public Generator(String name) {
        this.name = name;
        this.dropItem = new ItemStack(Material.DIRT);
    }

    public Generator(String name, ItemStack genItem, long delay, ItemStack dropItem, ArrayList<Location> locationList) {
        this.name = name;
        this.genItem = genItem;
        this.delay = delay;
        this.dropItem = dropItem;
        this.locationList = locationList;
    }

    public void start() {
        if (task != null) {
            task.cancel();
        }
        task = Bukkit.getScheduler().runTaskTimer(CataGens.getInstance(), this, 0, delay);
    }

    public void stop() {
        if (task == null) return;
        task.cancel();
    }

    @Override
    public void run() {
        for (Location location : locationList) {
            location.getWorld().dropItemNaturally(new Location(location.getWorld(), location.getX(), location.getY() + 0.75, location.getZ()), dropItem);
        }
    }

    public void save() {

        if (configuration == null) {
            file = new File(CataGens.getInstance().getDataFolder() + "/generators/" + name + ".yml");
            configuration = YamlConfiguration.loadConfiguration(file);
        }

        configuration.set("Generator", this);

        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLocation(Location location) {
        locationList.add(location);
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> serializedMap = new LinkedHashMap<>();

        serializedMap.put("name", name);
        serializedMap.put("genItem", genItem == null ? null : genItem.serialize());
        serializedMap.put("delay", String.valueOf(delay));
        serializedMap.put("dropItem", dropItem == null ? null : dropItem.serialize());

        Map<String, Object> tempLocMap = new LinkedHashMap<>();
        for (int i = 0; i < locationList.size(); i++) {
            tempLocMap.put(String.valueOf(i + 1), locationList.get(i).serialize());
        }
        serializedMap.put("locations", tempLocMap);

        return serializedMap;
    }

    public static Generator deserialize(Map<String, Object> map) {

        String name = String.valueOf(UUID.randomUUID());
        if (map.containsKey("name")) {
            name = (String) map.get("name");
        }

        ItemStack genItem = null;
        if (map.containsKey("genItem") && map.get("genItem") != null) {
            genItem = ItemStack.deserialize((Map<String, Object>) map.get("genItem"));
        }

        long delay = 60;
        if (map.containsKey("delay")) {
            delay = Long.parseLong((String) map.get("delay"));
        }

        ItemStack dropItem = new ItemStack(Material.DIRT);
        if (map.containsKey("dropItem") && map.get("dropItem") != null) {
            dropItem = ItemStack.deserialize((Map<String, Object>) map.get("dropItem"));
        }

        ArrayList<Location> locs = new ArrayList<>();
        if (map.containsKey("locations")) {
            Map<String, Object> tempLocMap = (Map<String, Object>) map.get("locations");
            tempLocMap.forEach((k, v) -> locs.add(Location.deserialize((Map<String, Object>) v)));
        }

        return new Generator(name, genItem, delay, dropItem, locs);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemStack getGenItem() {
        return genItem;
    }

    public void setGenItem(ItemStack genItem) {
        this.genItem = genItem;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public ItemStack getDropItem() {
        return dropItem;
    }

    public void setDropItem(ItemStack dropItem) {
        this.dropItem = dropItem;
    }

    public ArrayList<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(ArrayList<Location> locationList) {
        this.locationList = locationList;
    }

    public File getFile() {
        return file;
    }
}
