package org.minecraft.trident.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.minecraft.trident.modules.CommandModule;

import java.util.Date;

public class ban extends CommandModule {
    private final static String BANNED_PLAYER_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eBanned &f%s");

    private String joinArgs(String[] args) {
        StringBuilder joinedString = new StringBuilder();

        for (int i = 1; i < args.length; i++) { //Iteration starts at 1 to skip target player
            joinedString.append(args[i]).append(" ");
        }

        return joinedString.toString();
    }

    public ban() {
        super("ban", 1, -1);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            final OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[0]);
            final Player onlineTarget = Bukkit.getPlayer(args[0]);

            Bukkit.getBanList(BanList.Type.NAME).addBan(offlineTarget.getName(), joinArgs(args), new Date(2100, 1, 1), sender.getName());

            if (onlineTarget != null) {
                onlineTarget.kickPlayer("disconnected");
            }

            player.sendMessage(String.format(BANNED_PLAYER_MESSAGE, offlineTarget.getName()));
        }
    }
}