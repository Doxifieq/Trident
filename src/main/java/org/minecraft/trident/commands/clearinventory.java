package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;

public class clearinventory extends CommandModule {
    private final static String CLEAR_INVENTORY_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eCleared inventory of &f%s");

    private final static String PLAYER_NOT_EXIST_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cSpecified player does not exist");

    public clearinventory() {
        super("clearinventory", 0, 1);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.getInventory().clear();

                player.sendMessage(String.format(CLEAR_INVENTORY_MESSAGE, player.getName()));
            }

            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    target.getInventory().clear();

                    player.sendMessage(String.format(CLEAR_INVENTORY_MESSAGE, target.getName()));
                } else {
                    player.sendMessage(PLAYER_NOT_EXIST_MESSAGE);
                }
            }
        }
    }
}