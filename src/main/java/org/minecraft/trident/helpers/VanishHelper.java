package org.minecraft.trident.helpers;

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import org.minecraft.trident.Trident;

import java.util.ArrayList;
import java.util.List;

public class VanishHelper {
    private static VanishHelper INSTANCE = null;

    private static List<Player> VANISHED_PLAYERS;

    public void hidePlayer(Player player) {
        VANISHED_PLAYERS.add(player);

        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("trident.vanish")) continue;

            p.hidePlayer(Trident.INSTANCE, player);
        }
    }

    public void showPlayer(Player player) {
        VANISHED_PLAYERS.remove(player);

        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.showPlayer(Trident.INSTANCE, player);
        }
    }

    public void removePlayerFromVanishedList(Player player) {
        VANISHED_PLAYERS.remove(player);
    }

    public boolean isVanished(Player player) {
        return VANISHED_PLAYERS.contains(player);
    }

    private VanishHelper() {
        VANISHED_PLAYERS = new ArrayList<>();
    }

    public static synchronized VanishHelper getInstance() {
        if (INSTANCE == null)
            INSTANCE = new VanishHelper();

        return INSTANCE;
    }
}