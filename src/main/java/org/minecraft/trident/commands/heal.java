package org.minecraft.trident.commands;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;

public class heal extends CommandModule {
    private final static String HEAL_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eHealed &f%s");

    private final static String PLAYER_NOT_EXIST_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cSpecified player does not exist");

    public heal() {
        super("heal", 0, 1);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 0) {
                final double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

                player.setSaturation(20);
                player.setFoodLevel(20);

                player.setHealth(maxHealth);

                player.sendMessage(String.format(HEAL_MESSAGE, player.getName()));
            }

            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    final double maxHealth = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();

                    target.setSaturation(20);
                    target.setFoodLevel(20);

                    target.setHealth(maxHealth);

                    player.sendMessage(String.format(HEAL_MESSAGE, target.getName()));
                } else {
                    player.sendMessage(PLAYER_NOT_EXIST_MESSAGE);
                }
            }
        }
    }
}