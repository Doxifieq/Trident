package org.minecraft.trident;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;
import org.minecraft.trident.commands.*;
import org.minecraft.trident.events.*;

import java.util.HashMap;

public final class Trident extends JavaPlugin {
    public static Trident INSTANCE;

    public static HashMap<String, CommandModule> COMMANDS;
    public static HashMap<Player, Player> TPA_REQUESTS;

    @Override
    public void onEnable() {
        INSTANCE = this;

        COMMANDS = new HashMap<>();
        TPA_REQUESTS = new HashMap<>();

        registerCommands();
        registerEvents();

        if (!getDataFolder().exists()) {
            if (getDataFolder().mkdir()) {
                Bukkit.getLogger().info("Created plugin data folder.");
            } else {
                Bukkit.getLogger().severe("Failed to create plugin data folder.");
            }
        }
    }

    @Override
    public void onDisable() {
        INSTANCE = null;

        COMMANDS.clear();
    }

    private void registerCommands() {
        new clearinventory();
        new tpaccept();
        new gamemode();
        new teleport();
        new sethome();
        new tpdeny();
        new tphere();
        new vanish();
        new night();
        new heal();
        new feed();
        new give();
        new item();
        new home();
        new kick();
        new fly();
        new day();
        new tpa();
        new ban();
    }

    private void registerEvents() {
        new PlayerJoinEventListener();
        new PlayerQuitEventListener();
    }
}