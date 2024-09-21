package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import org.minecraft.trident.modules.CommandModule;

public class fly extends CommandModule {
    private final static String GAME_MODE_CHANGED_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eSet flight mode to &f%s");

    public fly() {
        super("fly", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            player.setAllowFlight(!player.getAllowFlight());

            player.sendMessage(String.format(GAME_MODE_CHANGED_MESSAGE, player.getAllowFlight() ? "Enabled" : "Disabled"));
        }
    }
}