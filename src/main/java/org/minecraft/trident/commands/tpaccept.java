package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import org.minecraft.trident.modules.CommandModule;
import org.minecraft.trident.Trident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tpaccept extends CommandModule {
    private static final String REQUEST_ACCEPTED_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eYour teleport request to &f%s &ehas been accepted");

    private final static String NO_INCOMING_REQUESTS_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cNo incoming teleport requests");

    private static final HashMap<Player, Player> TPA_REQUESTS = Trident.TPA_REQUESTS;

    private List<Player> getPendingRequests(Player player) {
        final List<Player> players = new ArrayList<>();

        for (Map.Entry<Player, Player> entry : TPA_REQUESTS.entrySet()) {
            if (entry.getValue() == player) {
                players.add(entry.getKey());
            }
        }

        return players;
    }

    private void teleportPlayers(Player player, List<Player> players) {
        for (final Player p : players) {
            p.teleport(player.getLocation());

            p.sendMessage(String.format(REQUEST_ACCEPTED_MESSAGE, player.getName()));

            Trident.TPA_REQUESTS.remove(p);
        }
    }

    public tpaccept() {
        super("tpaccept", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            final List<Player> players = getPendingRequests(player);

            if (!players.isEmpty()) {
                teleportPlayers(player, players);
            } else {
                player.sendMessage(NO_INCOMING_REQUESTS_MESSAGE);
            }
        }
    }
}