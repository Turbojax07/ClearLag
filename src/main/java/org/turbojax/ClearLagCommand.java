package org.turbojax;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.turbojax.configs.MainConfig;
import org.turbojax.configs.Messages;

public class ClearLagCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 0) {
            sender.sendMessage(Messages.formatMessage(Messages.help));
            return true;
        }

        switch (args[0]) {
            case "help":
                if (!sender.hasPermission("clearlag.command.help")) {
                    sender.sendMessage(Messages.formatMessage(Messages.noPerms));
                    break;
                }

                sender.sendMessage(Messages.formatMessage(Messages.help));
                break;
            case "now":
                if (!sender.hasPermission("clearlag.command.now")) {
                    sender.sendMessage(Messages.formatMessage(Messages.noPerms));
                    break;
                }

                Bukkit.broadcast(Messages.formatMessage(Messages.immediateClearStart));
                Daemon.getInstance().clear();
                Bukkit.broadcast(Messages.formatMessage(Messages.immediateClearSuccess));
                break;
            case "reload":
                if (!sender.hasPermission("clearlag.command.reload")) {
                    sender.sendMessage(Messages.formatMessage(Messages.noPerms));
                    break;
                }

                // Reloading and printing the success message if all goes well
                sender.sendMessage(Messages.formatMessage(Messages.reloadStart));
                if (MainConfig.reloadConfigs() && Messages.reloadConfigs()) sender.sendMessage(Messages.formatMessage(Messages.reloadSuccess));
                else sender.sendMessage(Messages.formatMessage(Messages.reloadFail));
                break;
            case "time":
                if (!sender.hasPermission("clearlag.command.time")) {
                    sender.sendMessage(Messages.formatMessage(Messages.noPerms));
                    break;
                }

                sender.sendMessage(Messages.formatMessage(Messages.timeLeft));
                break;
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1) return List.of("help", "now", "reload", "time");

        return new ArrayList<>();
    }
}