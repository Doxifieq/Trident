package org.minecraft.trident.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.ChatColor;

import org.minecraft.trident.modules.CommandModule;
import org.minecraft.trident.Trident;

public class CommandHandler implements CommandExecutor {
    private final String INVALID_ARGUMENTS_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cInvalid arguments for &f/%s");

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final String commandName = command.getName();

        if (Trident.COMMANDS.containsKey(commandName)) {
            CommandModule commandModule = Trident.COMMANDS.get(commandName);

            if (args.length >= commandModule.minArgs && args.length <= commandModule.maxArgs) {
                commandModule.run(sender, args);
            } else {
                sender.sendMessage(String.format(INVALID_ARGUMENTS_MESSAGE, commandName));
            }
        }

        return false;
    }
}