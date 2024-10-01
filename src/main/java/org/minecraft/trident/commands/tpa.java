package org.minecraft.trident.commands;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;
import org.minecraft.trident.helpers.VanishHelper;
import org.minecraft.trident.Trident;

import java.util.HashMap;

public class tpa extends CommandModule {
    private static final String REQUEST_RECEIVED_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eReceived teleport request from &f%s\n &7- &eThis request will expire in &f30 &eseconds\n&0\n &7- &f/tpaccept &eto accept the request\n &7- &f/tpdeny &eto deny the request");
    private static final String REQUEST_SENT_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eTeleport request sent to &f%s");

    private static final String REQUEST_EXPIRED_TARGET_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cThe teleport request from &f%s &chas expired");
    private static final String REQUEST_EXPIRED_SELF_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cThe teleport request to &f%s &chas expired");
    private static final String REQUEST_SENT_SELF_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cYou cannot send a teleport request to yourself");
    private static final String OVERLOAD_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cYou are sending too many teleport requests");
    private final static String PLAYER_NOT_EXIST_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cSpecified player does not exist");

    private static final HashMap<Player, Player> TPA_REQUESTS = Trident.TPA_REQUESTS;

    public tpa() {
        super("tpa", 1, 1);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            final Player target = Bukkit.getPlayer(args[0]);

            if (target == null || VanishHelper.getInstance().isVanished(target)) { //Prevent player from sending teleport requests to vanished players and make sure Target is not null
                player.sendMessage(PLAYER_NOT_EXIST_MESSAGE);

                return;
            }

            if (target == player) { //Prevent player from sending a teleportation request to themselves
                player.sendMessage(REQUEST_SENT_SELF_MESSAGE);

                return;
            }

            if (TPA_REQUESTS.get(player) == null) {
                TPA_REQUESTS.put(player, target); //Create a teleportation request ( Self --> Target )

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (TPA_REQUESTS.containsKey(player) && TPA_REQUESTS.get(player) == target) { //Is the request still active?
                            TPA_REQUESTS.remove(player);

                            target.sendMessage(String.format(REQUEST_EXPIRED_TARGET_MESSAGE, player.getName()));
                            player.sendMessage(String.format(REQUEST_EXPIRED_SELF_MESSAGE, target.getName()));
                        }
                    }
                }.runTaskLater(Trident.INSTANCE, 600L); //30 seconds

                target.sendMessage(String.format(REQUEST_RECEIVED_MESSAGE, player.getName()));
                player.sendMessage(String.format(REQUEST_SENT_MESSAGE, target.getName()));
            } else {
                player.sendMessage(OVERLOAD_MESSAGE);
            }
        }
    }
}