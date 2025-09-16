package org.turbojax.configs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.turbojax.ClearLag;
import org.turbojax.Daemon;

public class MainConfig {
    private static final File file = new File("plugins/ClearLag/config.yml");
    private static final FileConfiguration config = new YamlConfiguration();

    /**
     * Reloads the configuration.
     *
     * @return Whether the configuration was loaded successfully.
     */
    public static boolean reloadConfigs() {
        // Creating the file
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException err) {
                ClearLag.getInstance().getSLF4JLogger().error(Messages.cannotCreateFile.replace("%file_path%", file.getPath()));
                return false;
            }
        }

        // Loading the config
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException err) {
            ClearLag.getInstance().getSLF4JLogger().error(Messages.noConfigFile.replace("%file_path%", file.getPath()), err);
            return false;
        }

        // Getting values from the config
        clearInterval = config.getInt("clear-interval");
        warning1 = config.getInt("warning-1");
        warning2 = config.getInt("warning-2");
        clearNamedEntities = config.getBoolean("clear-named-entities");
        minimumLivedSeconds = config.getDouble("minimum-lived-seconds");
        playerSafeRadius = config.getInt("player-safe-radius");

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

    // The amount of seconds an entity needs to be alive before it can be removed by ClearLag
    public static double minimumLivedSeconds = 1;

    // The radius in blocks around each player where mobs and items will not despawn.
    public static int playerSafeRadius = 64;

    // A list of entities to not delete
    public static List<EntityType> excludedEntities = new ArrayList<>(List.of(EntityType.PLAYER, EntityType.ITEM_FRAME, EntityType.ARMOR_STAND, EntityType.VILLAGER));
}
