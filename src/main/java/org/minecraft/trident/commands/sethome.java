package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import org.minecraft.trident.modules.CommandModule;
import org.minecraft.trident.helpers.DataHelper;

public class sethome extends CommandModule {
    private static final String HOME_SET_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eHome has been set to your location");

    public sethome() {
        super("sethome", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            final String UUID = player.getUniqueId().toString();
            final Location location = player.getLocation();

            DataHelper.getInstance().Set(UUID, "home", location);

            player.sendMessage(HOME_SET_MESSAGE);
        }
    }
}