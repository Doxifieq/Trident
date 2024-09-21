package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;

public class feed extends CommandModule {
    private final static String FEED_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eYour appetite has been sated");

    private final static String PLAYER_NOT_EXIST_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cSpecified player does not exist");

    public feed() {
        super("feed", 0, 1);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.setSaturation(20);
                player.setFoodLevel(20);

                player.sendMessage(FEED_MESSAGE);
            }

            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    target.setSaturation(20);
                    target.setFoodLevel(20);

                    target.sendMessage(FEED_MESSAGE);
                } else {
                    player.sendMessage(PLAYER_NOT_EXIST_MESSAGE);
                }
            }
        }
    }
}