package org.minecraft.trident.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import org.minecraft.trident.modules.CommandModule;
import org.minecraft.trident.helpers.VanishHelper;

public class vanish extends CommandModule {
    private static final String SHOW_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eYou are no longer hidden");
    private static final String HIDE_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eYou are now hidden");

    public vanish() {
        super("vanish", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            final boolean isVanished = VanishHelper.getInstance().isVanished(player);

            if (isVanished) {
                VanishHelper.getInstance().showPlayer(player);

                player.sendMessage(SHOW_MESSAGE);
            } else {
                VanishHelper.getInstance().hidePlayer(player);

                player.sendMessage(HIDE_MESSAGE);
            }
        }
    }
}