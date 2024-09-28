package org.minecraft.trident.events;

import org.bukkit.event.player.PlayerJoinEvent;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import org.minecraft.trident.helpers.VanishHelper;
import org.minecraft.trident.Trident;

public class PlayerJoinEventListener implements Listener {
    public PlayerJoinEventListener() {
        Bukkit.getPluginManager().registerEvents(this, Trident.INSTANCE);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        for (final Player p : Bukkit.getOnlinePlayers()) {
            final boolean isVanished = VanishHelper.getInstance().isVanished(p);

            if (isVanished) {
                player.hidePlayer(Trident.INSTANCE, p);
            }
        }
    }
}