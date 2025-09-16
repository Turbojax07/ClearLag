package org.turbojax.configs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.turbojax.ClearLag;

public class Messages {
    private static final File file = new File("plugins/ClearLag/messages.yml");
    private static final FileConfiguration config = new YamlConfiguration();
    private static final MiniMessage serializer = MiniMessage.miniMessage();

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
        if (!config.contains("message-not-found")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "message-not-found"));
        } else {
            messageNotFound = config.getString("message-not-found");
        }

        if (!config.contains("no-config-file")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "no-config-file"));
        } else {
            noConfigFile = config.getString("no-config-file");
        }

        if (!config.contains("cannot-create-file")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "cannot-create-file"));
        } else {
            cannotCreateFile = config.getString("cannot-create-file");
        }
        
        if (!config.contains("papi-found")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "papi-found"));
        } else {
            papiFound = config.getString("papi-found");
        }
        
        if (!config.contains("plugin-loaded")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "plugin-loaded"));
        } else {
            pluginLoaded = config.getString("plugin-loaded");
        }
        
        if (!config.contains("first-warn")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "first-warn"));
        } else if (config.isList("first-warn")) {
            warn.clear();
            warn.addAll(config.getStringList("first-warn"));
        } else {
            warn = Collections.singletonList(config.getString("first-warn"));
        }

        if (!config.contains("items-cleared")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "items-cleared"));
        } else if (config.isList("items-cleared")) {
            itemsCleared.clear();
            itemsCleared.addAll(config.getStringList("items-cleared"));
        } else {
            itemsCleared = Collections.singletonList(config.getString("items-cleared"));
        }

        if (!config.contains("time-left")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "time-left"));
        } else if (config.isList("time-left")) {
            timeLeft.clear();
            timeLeft.addAll(config.getStringList("time-left"));
        } else {
            timeLeft = Collections.singletonList(config.getString("time-left"));
        }

        if (!config.contains("no-perms")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "no-perms"));
        } else if (config.isList("no-perms")) {
            noPerms.clear();
            noPerms.addAll(config.getStringList("no-perms"));
        } else {
            noPerms = Collections.singletonList(config.getString("no-perms"));
        }

        if (!config.contains("reload-start")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "reload-start"));
        } else if (config.isList("reload-start")) {
            reloadStart.clear();
            reloadStart.addAll(config.getStringList("reload-start"));
        } else {
            reloadStart = Collections.singletonList(config.getString("reload-start"));
        }

        if (!config.contains("reload-success")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "reload-success"));
        } else if (config.isList("reload-success")) {
            reloadSuccess.clear();
            reloadSuccess.addAll(config.getStringList("reload-success"));
        } else {
            reloadSuccess = Collections.singletonList(config.getString("reload-success"));
        }

        if (!config.contains("reload-fail")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "reload-fail"));
        } else if (config.isList("reload-fail")) {
            reloadFail.clear();
            reloadFail.addAll(config.getStringList("reload-fail"));
        } else {
            reloadFail = Collections.singletonList(config.getString("reload-fail"));
        }

        if (!config.contains("immediate-clear-start")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "immediate-clear-start"));
        } else if (config.isList("immediate-clear-start")) {
            immediateClearStart.clear();
            immediateClearStart.addAll(config.getStringList("immediate-clear-start"));
        } else {
            immediateClearStart = Collections.singletonList(config.getString("immediate-clear-start"));
        }

        if (!config.contains("immediate-clear-success")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "immediate-clear-success"));
        } else if (config.isList("immediate-clear-success")) {
            immediateClearSuccess.clear();
            immediateClearSuccess.addAll(config.getStringList("immediate-clear-success"));
        } else {
            immediateClearSuccess = Collections.singletonList(config.getString("immediate-clear-success"));
        }

        if (!config.contains("help")) {
            ClearLag.getInstance().getSLF4JLogger().warn(Messages.messageNotFound.replace("%msg_label%", "help"));
        } else if (config.isList("help")) {
            help.clear();
            help.addAll(config.getStringList("help"));
        } else {
            help = Collections.singletonList(config.getString("help"));
        }

        return true;
    }

    /**
     * Converts a list of strings to a component.
     * It applies any placeholders and uses the minimessage deserializer to convert the strings to Components.
     *
     * @param message The list of strings to convert.
     *
     * @return A component representing the list of strings.
     */
    public static Component formatMessage(List<String> message) {
        return Component.join(JoinConfiguration.newlines(), message.stream().map(line -> serializer.deserialize(PlaceholderAPI.setPlaceholders(null, line))).toList());
    }

    /**
     * Applies placeholders to each string in a list of strings.
     *
     * @param message The list of strings to apply placeholders to.
     *
     * @return A list of strings with the placeholders set.
     */
    public static List<String> applyPlaceholders(List<String> message) {
        return message.stream().map(line -> PlaceholderAPI.setPlaceholders(null, line)).toList();
    }

    // String only
    public static String papiFound = "PlaceholderAPI enabled";
    public static String pluginLoaded = "ClearLag setup complete! Next clear is in %clearlag_time_left% seconds.";
    public static String messageNotFound = "Could not retrieve the \"%msg_label%\" message from the config.";
    public static String noConfigFile = "Cannot load config file \"%file_path%\"";
    public static String cannotCreateFile = "Cannot create file \"%file_path%\"";

    // List allowed
    public static List<String> warn = new ArrayList<>(List.of("<gray>[<#FA8128>ClearLag<gray>] <white>Clearing dropped items and unnecessary entities in <#07FB0A>%clearlag_time_left% <white>seconds!"));
    public static List<String> itemsCleared = new ArrayList<>(List.of("<gray>[<#FA8128>ClearLag<gray>] <white>All dropped items and unnecessary entities have been cleared!"));
    public static List<String> timeLeft = new ArrayList<>(List.of("<gray>[<#FA8128>ClearLag<gray>] <white>Next clear is in <#07FB0A>%clearlag_time_left%<white> seconds."));

    public static List<String> noPerms = new ArrayList<>(List.of("<gray>[<#FA8128>ClearLag<gray>] <red>You do not have permission to use this command!"));

    public static List<String> reloadStart = new ArrayList<>(List.of("<gray>[<#FA8128>ClearLag<gray>] <white>Reloading ClearLag configs..."));
    public static List<String> reloadSuccess = new ArrayList<>(List.of("<gray>[<#FA8128>ClearLag<gray>] <white>ClearLag configs reloaded successfully!  Next clear in <#07FB0A>%clearlag_time_left% <white>seconds."));
    public static List<String> reloadFail = new ArrayList<>(List.of("<gray>[<#FA8128>ClearLag<gray>] <white>Could not reload configs."));

    public static List<String> immediateClearStart = new ArrayList<>(List.of("<gray>[<#FA8128>ClearLag<gray>] <white>An admin has triggered an immediate clear!"));
    public static List<String> immediateClearSuccess = new ArrayList<>(List.of("<gray>[<#FA8128>ClearLag<gray>] <white>Immediate clearing completed!"));

    public static List<String> help = new ArrayList<>(List.of(
            "<gray><st>          </st>< 🔥 <#07FB0A>Clearlag Commands 🔥 <gray>><st>          </st>",
            "",
            "<#07FB0A>/clearlag help <white>- Displays this help menu with command summaries.",
            "<#07FB0A>/clearlag now <white>- Immediately clear all items and entities.",
            "<#07FB0A>/clearlag reload <white>- Reload the ClearLag plugin.",
            "<#07FB0A>/clearlag time <white>- Shows time remaining until the next clear.",
            ""
    ));
}
