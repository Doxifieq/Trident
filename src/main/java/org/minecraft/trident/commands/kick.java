package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;

public class kick extends CommandModule {
    private final static String KICKED_PLAYER_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eKicked &f%s");

    private final static String PLAYER_NOT_EXIST_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cSpecified player does not exist");

    private String joinArgs(String[] args) {
        StringBuilder joinedString = new StringBuilder();

        for (int i = 1; i < args.length; i++) { //Iteration starts at 1 to skip target player
            joinedString.append(args[i]).append(" ");
        }

        return joinedString.toString();
    }

    public kick() {
        super("kick", 1, -1);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            final Player target = Bukkit.getPlayer(args[0]);

            if (target != null) {
                target.kickPlayer(joinArgs(args));

                player.sendMessage(String.format(KICKED_PLAYER_MESSAGE, target.getName()));
            } else {
                player.sendMessage(PLAYER_NOT_EXIST_MESSAGE);
            }
        }
    }
}