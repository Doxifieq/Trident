package org.minecraft.trident;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import org.minecraft.trident.modules.CommandModule;
import org.minecraft.trident.commands.*;

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
        new tpdeny();
        new tphere();
        new night();
        new heal();
        new feed();
        new give();
        new item();
        new fly();
        new day();
        new tpa();
    }
}