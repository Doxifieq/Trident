package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;
import org.minecraft.trident.Trident;

public class tpa extends CommandModule {
    private static final String REQUEST_RECEIVED_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eReceived teleport request from &f%s\n &7- &eThis request will expire in &f30 &eseconds\n&0\n &7- &f/tpaccept &eto accept the request\n &7- &f/tpdeny &eto deny the request");
    private static final String REQUEST_SENT_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eTeleport request sent to &f%s");

    public tpa() {
        super("tpa", 1, 1);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            final Player target = Bukkit.getPlayer(args[0]);

            if (target != null) {
                Trident.TPA_REQUESTS.put(player, target);

                target.sendMessage(String.format(REQUEST_RECEIVED_MESSAGE, player.getName()));
                player.sendMessage(String.format(REQUEST_SENT_MESSAGE, target.getName()));
            }
        }
    }
}