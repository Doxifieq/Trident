package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import org.minecraft.trident.modules.CommandModule;

public class night extends CommandModule {
    private final static String NIGHT_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &Set time to &f13000");

    public night() {
        super("night", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            player.getWorld().setTime(1000);

            player.sendMessage(NIGHT_MESSAGE);
        }
    }
}