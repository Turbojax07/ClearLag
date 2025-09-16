package org.turbojax;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.turbojax.configs.MainConfig;
import org.turbojax.configs.Messages;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Daemon extends BukkitRunnable {
    private static Daemon instance;
    private int countdown = 0;

    public Daemon() {
        instance = this;
    }

    public static Daemon getInstance() {
        return instance;
    }

    /**
     * Returns the amount of time until the next clear.
     *
     * @return The amount of time until the next clear in seconds.
     */
    public int getNextClear() {
        return countdown;
    }

    /** Clears all entities that match the parameters laid out in the config. */
    public void clear() {
        Bukkit.getScheduler().runTask(ClearLag.getInstance(), () -> {
            // Finding all the mobs close to a player
            Set<UUID> toIgnore = new HashSet<>();
            Bukkit.getOnlinePlayers().forEach(player -> {
                toIgnore.addAll(player.getNearbyEntities(MainConfig.playerSafeRadius, MainConfig.playerSafeRadius, MainConfig.playerSafeRadius).stream().map(Entity::getUniqueId).collect(Collectors.toSet()));
            });

            // Finding all the entities in every world.
            for (World world : Bukkit.getWorlds()) {
                world.getEntities().forEach(entity -> {
                    // Skipping entities that have not been alive long enough to be removed.
                    if (entity.getTicksLived() / 20.0 < MainConfig.minimumLivedSeconds) return;

                    // Skipping entities that are close enough to a player.
                    if (toIgnore.contains(entity.getUniqueId())) return;

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
     * Sets the countdown timer.
     *
     * @param countdown The time to set the counter to in seconds
     */
    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }
}