package org.turbojax;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClearLagPAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "clearlag";
    }

    @Override
    public @NotNull String getAuthor() {
        return "TurboJax07";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.1";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        Daemon daemon = Daemon.getInstance();

        if (daemon == null) return "";

        if (params.equalsIgnoreCase("time_left")) {
            String timeLeft = String.valueOf(daemon.getNextClear());
            ClearLag.getInstance().getSLF4JLogger().info(timeLeft);
            return timeLeft;
        }

        return "";
    }
}