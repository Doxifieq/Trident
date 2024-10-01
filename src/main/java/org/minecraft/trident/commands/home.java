package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import org.minecraft.trident.helpers.DataHelper;
import org.minecraft.trident.modules.CommandModule;

public class home extends CommandModule {
    private static final String TELEPORT_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eTeleporting...");

    private static final String NO_HOME_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cYou do not have a home set, create one with &f/sethome");

    public home() {
        super("home", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            final String UUID = player.getUniqueId().toString();

            final Location location = (Location) DataHelper.getInstance().Get(UUID, "home");

            if (location != null) {
                player.teleport(location);

                player.sendMessage(TELEPORT_MESSAGE);
            } else {
                player.sendMessage(NO_HOME_MESSAGE);
            }
        }
    }
}