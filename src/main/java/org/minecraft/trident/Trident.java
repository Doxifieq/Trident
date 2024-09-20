package org.minecraft.trident;

import org.bukkit.plugin.java.JavaPlugin;
import org.minecraft.trident.commands.Fly;
import org.minecraft.trident.modules.CommandModule;

import java.util.HashMap;

public final class Trident extends JavaPlugin {
    public static Trident INSTANCE;

    public static HashMap<String, CommandModule> COMMANDS;

    @Override
    public void onEnable() {
        INSTANCE = this;

        COMMANDS = new HashMap<>();

        registerCommands();
    }

    @Override
    public void onDisable() {
        INSTANCE = null;

        COMMANDS.clear();
    }

    private void registerCommands() {
        new Fly();
    }
}