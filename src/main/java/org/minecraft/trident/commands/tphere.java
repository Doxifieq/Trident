package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;

public class tphere extends CommandModule {
    private final static String TELEPORT_PLAYER_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eTeleporting &f%s &eto &f%s");

    private final static String PLAYER_NOT_EXIST_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cSpecified player does not exist");

    public tphere() {
        super("tphere", 1, 1);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target != null) {
                target.teleport(player.getLocation());

                player.sendMessage(String.format(TELEPORT_PLAYER_MESSAGE, target.getName(), player.getName()));
            } else {
                player.sendMessage(PLAYER_NOT_EXIST_MESSAGE);
            }
        }
    }
}