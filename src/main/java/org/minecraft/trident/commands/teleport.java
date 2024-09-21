package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;

public class teleport extends CommandModule {
    private final static String TELEPORT_PLAYER_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eTeleporting &f%s &eto &f%s");

    private final static String PLAYER_NOT_EXIST_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cSpecified player does not exist");

    public teleport() {
        super("teleport", 1, 2);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    player.teleport(target.getLocation());

                    player.sendMessage(String.format(TELEPORT_PLAYER_MESSAGE, player.getName(), target.getName()));
                } else {
                    player.sendMessage(PLAYER_NOT_EXIST_MESSAGE);
                }
            }

            if (args.length == 2) {
                Player target1 = Bukkit.getPlayer(args[0]);
                Player target2 = Bukkit.getPlayer(args[1]);

                if (target1 != null && target2 != null) {
                    target1.teleport(target2.getLocation());

                    player.sendMessage(String.format(TELEPORT_PLAYER_MESSAGE, target1.getName(), target2.getName()));
                } else {
                    player.sendMessage(PLAYER_NOT_EXIST_MESSAGE);
                }
            }
        }
    }
}