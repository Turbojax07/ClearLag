package org.turbojax.clearLag.configs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.turbojax.clearLag.ClearLag;
import org.turbojax.clearLag.Daemon;

public class MainConfig {
    private static File file;

    public MainConfig(File file) {
        MainConfig.file = file;
        reloadConfigs();
    }

    public static boolean reloadConfigs() {
        // Creating the file
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException err) {
                ClearLag.getInstance().getSLF4JLogger().error("Cannot create file \"{}\"", file.getPath());
                return false;
            }
        }

        // Loading the config
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        // Getting values from the config
        clearInterval = config.getInt("clear-interval");
        warning1 = config.getInt("warning-1");
        warning2 = config.getInt("warning-2");
        clearNamedEntities = config.getBoolean("clear-named-entities");

        if (!excludedEntities.isEmpty()) excludedEntities.clear();
        excludedEntities.addAll(config.getStringList("excluded-entities").stream().map(EntityType::fromName).toList());

        Daemon daemon = Daemon.getInstance();
        if (daemon != null) daemon.setCountdown(clearInterval);

        return true;
    }

    // Total interval in seconds for clearing items/entities
    public static int clearInterval = 300;

    // First warning time in seconds before clearing
    public static int warning1 = 30;

    // Second warning time in seconds before clearing
    public static int warning2 = 10;

    // Toggle whether to clear named entities
    public static boolean clearNamedEntities = false;

    // A list of entities to not delete
    public static List<EntityType> excludedEntities = new ArrayList<>();

    static {
        excludedEntities.add(EntityType.PLAYER);
        excludedEntities.add(EntityType.ITEM_FRAME);
        excludedEntities.add(EntityType.ARMOR_STAND);
        excludedEntities.add(EntityType.VILLAGER);
    }
}