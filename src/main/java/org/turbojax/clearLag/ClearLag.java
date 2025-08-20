package org.turbojax.clearLag;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.turbojax.clearLag.configs.MainConfig;
import org.turbojax.clearLag.configs.Messages;

public final class ClearLag extends JavaPlugin {
    private static final Logger log = LoggerFactory.getLogger(ClearLag.class);
    private static ClearLag instance;

    private Daemon daemon;

    public static ClearLag getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // Loading configs
        saveResource("config.yml", false);
        saveResource("messages.yml", false);

        new MainConfig(new File(getDataFolder(), "config.yml"));
        new Messages(new File(getDataFolder(), "messages.yml"));

        // Creating the daemon and starting it up
        daemon = new Daemon();
        daemon.setCountdown(MainConfig.clearInterval);
        daemon.runTaskTimerAsynchronously(this, 0, 20);

        // Loading the PAPI extension
        new ClearLagPAPI().register();

        // Registering commands
        getCommand("clearlag").setExecutor(new ClearLagCommand());
        getCommand("clearlag").setTabCompleter(new ClearLagCommand());


        getSLF4JLogger().info("ClearLag setup complete! Next clear is in {} seconds.", daemon.getNextClear());
    }

    @Override
    public void onDisable() {
        if (daemon != null) daemon.cancel();
    }
}