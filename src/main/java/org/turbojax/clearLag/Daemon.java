package org.turbojax.clearLag;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.turbojax.clearLag.configs.MainConfig;
import org.turbojax.clearLag.configs.Messages;

public class Daemon extends BukkitRunnable {
    private static Daemon instance;
    private int countdown = 0;

    public Daemon() {
        instance = this;
    }

    public static Daemon getInstance() {
        return instance;
    }

    public int getNextClear() {
        return countdown;
    }

    public void clear() {
        Bukkit.getScheduler().runTask(ClearLag.getInstance(), () -> {
            for (World world : Bukkit.getWorlds()) {
                world.getEntities().forEach(entity -> {
                    // Skipping excluded entities
                    if (MainConfig.excludedEntities.contains(entity.getType())) return;

                    // Skipping named entities if enabled
                    if (!MainConfig.clearNamedEntities && entity.customName() != null) return;

                    // Deleting the entity
                    entity.remove();
                });
            }
        });
    }

    @Override
    public void run() {
        if (countdown == 0) {
            clear();
            Bukkit.broadcast(Messages.formatMessage(Messages.itemsCleared));
            countdown = MainConfig.clearInterval;
        }

        if (countdown == MainConfig.warning1) {
            Bukkit.broadcast(Messages.formatMessage(Messages.warn));
        }
        if (countdown == MainConfig.warning2) {
            Bukkit.broadcast(Messages.formatMessage(Messages.warn));
        }

        countdown--;
    }

    /**
     * Sets the countdown timer
     *
     * @param countdown The time to set the counter to in seconds
     */
    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }
}