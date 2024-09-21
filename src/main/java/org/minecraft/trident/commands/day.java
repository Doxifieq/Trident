package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import org.minecraft.trident.modules.CommandModule;

public class day extends CommandModule {
    private final static String DAY_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eSet time to &f1000");

    public day() {
        super("day", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            player.getWorld().setTime(1000);

            player.sendMessage(DAY_MESSAGE);
        }
    }
}