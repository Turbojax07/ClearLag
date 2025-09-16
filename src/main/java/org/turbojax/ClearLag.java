package org.turbojax;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.turbojax.configs.MainConfig;
import org.turbojax.configs.Messages;

public final class ClearLag extends JavaPlugin {
    private static ClearLag instance;
    private Daemon daemon;

    public static ClearLag getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // Saving default configs
        saveResource("config.yml", false);
        saveResource("messages.yml", false);

        // Loading configs into memory
        MainConfig.reloadConfigs();
        Messages.reloadConfigs();

        // Creating and starting the daemon
        daemon = new Daemon();
        daemon.setCountdown(MainConfig.clearInterval);
        daemon.runTaskTimerAsynchronously(this, 0, 20);

        // Loading the PAPI extension
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getSLF4JLogger().info(Messages.papiFound);
            new ClearLagPAPI().register();
        }

        // Registering commands
        getCommand("clearlag").setExecutor(new ClearLagCommand());
        getCommand("clearlag").setTabCompleter(new ClearLagCommand());
        
        getSLF4JLogger().info(PlaceholderAPI.setPlaceholders(null, Messages.pluginLoaded));
    }

    @Override
    public void onDisable() {
        if (daemon != null) daemon.cancel();
    }
}
