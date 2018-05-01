package com.mc.CDK2020.endquests;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class PrizeUtils {

    public static ItemStack dragonHelm() {

        ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
        item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        ItemMeta newMeta = item.getItemMeta();
        newMeta.setDisplayName(ChatColor.DARK_RED + "DragonKing Skull");
        List lore = Arrays.asList(new String[]{ChatColor.RED + "" + ChatColor.BOLD + "The DragonKing's Skull", ChatColor.AQUA + "forged from his dead body", ChatColor.GREEN + "Effects: Regeneration"});
        newMeta.setLore(lore);
        item.setItemMeta(newMeta);

        return item;

    }

    public static ItemStack dragonChest() {

        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        ItemMeta newMeta = item.getItemMeta();
        newMeta.setDisplayName(ChatColor.DARK_RED + "DragonKing Wings");
        List lore = Arrays.asList(new String[]{ChatColor.RED + "" + ChatColor.BOLD + "The DragonKing's Wings", ChatColor.AQUA + "forged from his dead body", ChatColor.GREEN + "Effects: Jump Boost"});
        newMeta.setLore(lore);
        item.setItemMeta(newMeta);

        return item;


    }

    public static ItemStack dragonPants() {

        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS);
        item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        ItemMeta newMeta = item.getItemMeta();
        newMeta.setDisplayName(ChatColor.DARK_RED + "DragonKing Tail");
        List lore = Arrays.asList(new String[]{ChatColor.RED + "" + ChatColor.BOLD + "The DragonKing's Scaly Tail", ChatColor.AQUA + "forged from his dead body", ChatColor.GREEN + "Effects: Fire Resistance"});
        newMeta.setLore(lore);
        item.setItemMeta(newMeta);

        return item;


    }

    public static ItemStack dragonBoots() {

        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
        item.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 5);

        ItemMeta newMeta = item.getItemMeta();
        newMeta.setDisplayName(ChatColor.DARK_RED + "DragonKing Claws");
        List lore = Arrays.asList(new String[]{ChatColor.RED + "" + ChatColor.BOLD + "The DragonKing's Claws", ChatColor.AQUA + "forged from his dead body", ChatColor.GREEN + "Effects: Speed"});
        newMeta.setLore(lore);
        item.setItemMeta(newMeta);

        return item;


    }
    public static ItemStack dragonBow() {

        ItemStack item = new ItemStack(Material.BOW);
        item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 3);
        item.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
        item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);

        ItemMeta newMeta = item.getItemMeta();
        newMeta.setDisplayName(ChatColor.DARK_RED + "DragonKing Fireball");
        List lore = Arrays.asList(new String[]{ChatColor.RED + "" + ChatColor.BOLD + "The DragonKing's Fireball", ChatColor.AQUA + "forged from his dead body", ChatColor.GREEN + "Effects: Invisibility"});
        newMeta.setLore(lore);
        item.setItemMeta(newMeta);

        return item;


    }

    public static ItemStack dragonSword() {

        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);
        item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 4);

        ItemMeta newMeta = item.getItemMeta();
        newMeta.setDisplayName(ChatColor.DARK_RED + "DragonKing Fang");
        List lore = Arrays.asList(new String[]{ChatColor.RED + "" + ChatColor.BOLD + "The DragonKing's Sharpest Fang", ChatColor.AQUA + "forged from his dead body", ChatColor.GREEN + "Effects: Strength"});
        newMeta.setLore(lore);
        item.setItemMeta(newMeta);

        return item;


    }


}
