package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Bukkit;

import org.minecraft.trident.modules.CommandModule;

public class give extends CommandModule {
    private final static String GIVE_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eGiving &f%s &eof &f%s &eto &f%s");

    private final static String PLAYER_NOT_EXIST_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&4⚠ &7| &cSpecified player does not exist");

    private String getStringAsProperCaseString(String item) {
        return item.toLowerCase().substring(0, 1).toUpperCase() + item.toLowerCase().substring(1);
    }

    private int getIntFromString(String s) {
        int amount;

        try {
            amount = Integer.parseInt(s);
        } catch (Exception e) {
            amount = 1;
        }

        if (amount < 1) amount = 1;

        return amount;
    }

    public give() {
        super("give", 3, 3);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            final Player target = Bukkit.getPlayer(args[0]);
            final Material material = Material.getMaterial(args[1].toUpperCase());

            if (target != null && material != null) {
                final int amount = getIntFromString(args[2]);

                final ItemStack item = new ItemStack(material);
                item.setAmount(amount);

                target.getInventory().addItem(item);

                player.sendMessage(String.format(GIVE_MESSAGE, amount, getStringAsProperCaseString(material.toString()), target.getName()));
            } else {
                player.sendMessage(PLAYER_NOT_EXIST_MESSAGE);
            }
        }
    }
}