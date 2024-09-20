package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.minecraft.trident.modules.CommandModule;

public class Fly extends CommandModule {
    public Fly() {
        super("fly", 0, 0);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            player.setAllowFlight(!player.getAllowFlight());
        }
    }
}