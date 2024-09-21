package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;

public class gamemode extends CommandModule {
    private final static String GAME_MODE_CHANGED_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eSet game mode to &f%s &efor &f%s");

    private final static String INVALID_MODE_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cSpecified mode is invalid");

    private static String getStringAsProperCaseString(String str) {
        return str.substring(0, 1).toUpperCase() + str.toLowerCase().substring(1);
    }

    private void setGameModeForPlayer(Player player, Player target, GameMode mode) {
        target.setGameMode(mode);

        final GameMode gameMode = target.getGameMode();

        player.sendMessage(String.format(GAME_MODE_CHANGED_MESSAGE, getStringAsProperCaseString(gameMode.toString()), target.getName()));
    }

    private void Switch(Player player, Player target, String mode) {
        switch (mode) {
            case "0", "s", "survival" -> setGameModeForPlayer(player, target, GameMode.SURVIVAL);
            case "1", "c", "creative" -> setGameModeForPlayer(player, target, GameMode.CREATIVE);
            case "2", "a", "adventure" -> setGameModeForPlayer(player, target, GameMode.ADVENTURE);
            case "3", "spec", "spectator" -> setGameModeForPlayer(player, target, GameMode.SPECTATOR);
            default -> player.sendMessage(INVALID_MODE_MESSAGE);
        }
    }

    public gamemode() {
        super("gamemode", 0, 2);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                final GameMode gameMode = player.getGameMode();
                final String mode = gameMode != GameMode.CREATIVE ? "creative" : "survival";

                Switch(player, player, mode);
            }

            if (args.length == 1) {
                final String mode = args[0].toLowerCase();

                Switch(player, player, mode);
            }

            if (args.length == 2) {
                final Player target = Bukkit.getPlayer(args[1]);

                if (target != null) {
                    final String mode = args[0].toLowerCase();

                    Switch(player, target, mode);
                }
            }
        }
    }
}