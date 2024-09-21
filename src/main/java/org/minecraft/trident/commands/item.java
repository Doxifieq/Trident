package org.minecraft.trident.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import org.minecraft.trident.modules.CommandModule;

public class item extends CommandModule {
    private final static String ITEM_MESSAGE = ChatColor.translateAlternateColorCodes('&', "&6ⓘ &7| &eGiving &f%s &eof &f%s &eto &f%s");

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

    public item() {
        super("item", 2, 2);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            final Material material = Material.getMaterial(args[0].toUpperCase());

            if (material != null) {
                final int amount = getIntFromString(args[1]);

                final ItemStack item = new ItemStack(material);
                item.setAmount(amount);

                player.getInventory().addItem(item);

                player.sendMessage(String.format(ITEM_MESSAGE, amount, getStringAsProperCaseString(material.toString()), player.getName()));
            }
        }
    }
}