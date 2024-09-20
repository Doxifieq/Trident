package org.minecraft.trident.modules;

import org.bukkit.command.CommandSender;

import org.minecraft.trident.commands.CommandHandler;
import org.minecraft.trident.Trident;

public abstract class CommandModule {
    public String label;
    public int minArgs;
    public int maxArgs;

    public CommandModule(String label, int minArgs, int maxArgs) {
        this.label = label;

        this.minArgs = minArgs;
        this.maxArgs = maxArgs;

        Trident.INSTANCE.getCommand(label).setExecutor(new CommandHandler());
        Trident.COMMANDS.put(label, this);
    }

    public abstract void run(CommandSender sender, String[] args);
}