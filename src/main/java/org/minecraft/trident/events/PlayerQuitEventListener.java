package org.minecraft.trident.events;

import org.bukkit.event.player.PlayerQuitEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import org.minecraft.trident.Trident;
import org.minecraft.trident.helpers.VanishHelper;

public class PlayerQuitEventListener implements Listener {
    public PlayerQuitEventListener() {
        Bukkit.getPluginManager().registerEvents(this, Trident.INSTANCE);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        VanishHelper.getInstance().removePlayerFromVanishedPlayersList(player);
    }
}