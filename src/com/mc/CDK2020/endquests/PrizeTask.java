package com.mc.CDK2020.endquests;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.spigotmc.*;

public class PrizeTask implements Runnable {

    // should be run asynchronously (every 2 sec?)

    public EndQuests _plugin;

    public PrizeTask(EndQuests plugin) {

        _plugin = plugin;

    }


    public void run() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            for (ItemStack armor : player.getInventory().getArmorContents()) {

                if (armor.hasItemMeta() && armor.getItemMeta().hasLore()) {

                    for (String lore : armor.getItemMeta().getLore()) {

                        if (lore.equals(ChatColor.AQUA + "forged from his dead body")) {

                            if (armor.getType() == Material.DIAMOND_HELMET) {

                                final Player finalPlayer = player;
                                Bukkit.getScheduler().runTaskLater(_plugin, new Runnable() {

                                    public void run(){
                                        if (finalPlayer.isOnline()) {
                                            boolean found = false;
                                            for (PotionEffect potionEffect : finalPlayer.getActivePotionEffects()) {
                                                if (potionEffect.getType() == PotionEffectType.REGENERATION
                                                        && potionEffect.getDuration() > 5*20) {
                                                    found = true;
                                                }
                                            }
                                            if (!found) finalPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 5*20, 1),true);
                                            finalPlayer.getWorld().playEffect(finalPlayer.getLocation(), Effect.ENDER_SIGNAL, 5, 30);
                                        }
                                    }

                                }, 1L);

                            } else if (armor.getType() == Material.DIAMOND_CHESTPLATE) {

                                final Player finalPlayer = player;
                                Bukkit.getScheduler().runTaskLater(_plugin, new Runnable() {

                                    public void run(){
                                        if (finalPlayer.isOnline()) {
                                            boolean found = false;
                                            for (PotionEffect potionEffect : finalPlayer.getActivePotionEffects()) {
                                                if (potionEffect.getType() == PotionEffectType.JUMP
                                                        && potionEffect.getDuration() > 5*20) {
                                                    found = true;
                                                }
                                            }
                                            if (!found) finalPlayer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 5*20, 0),true);
                                            Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, new Runnable() {
                                                public void run() {
                                                    finalPlayer.getWorld().playEffect(finalPlayer.getLocation(), Effect.ENDER_SIGNAL, 5, 30);
                                                }
                                            }, 10L);
                                        }
                                    }

                                }, 1L);

                            } else if (armor.getType() == Material.DIAMOND_LEGGINGS) {

                                final Player finalPlayer = player;
                                Bukkit.getScheduler().runTaskLater(_plugin, new Runnable() {

                                    public void run(){
                                        if (finalPlayer.isOnline()) {
                                            boolean found = false;
                                            for (PotionEffect potionEffect : finalPlayer.getActivePotionEffects()) {
                                                if (potionEffect.getType() == PotionEffectType.FIRE_RESISTANCE
                                                        && potionEffect.getDuration() > 5*20) {
                                                    found = true;
                                                }
                                            }
                                            if (!found) finalPlayer.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 5*20, 0),true);
                                            Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, new Runnable() {
                                                public void run() {
                                                    finalPlayer.getWorld().playEffect(finalPlayer.getLocation(), Effect.MOBSPAWNER_FLAMES, 5, 30);
                                                }
                                            }, 20L);
                                        }
                                    }

                                }, 1L);

                            } else if (armor.getType() == Material.DIAMOND_BOOTS) {

                                final Player finalPlayer = player;
                                Bukkit.getScheduler().runTaskLater(_plugin, new Runnable() {

                                    public void run(){
                                        if (finalPlayer.isOnline()) {
                                            boolean found = false;
                                            for (PotionEffect potionEffect : finalPlayer.getActivePotionEffects()) {
                                                if (potionEffect.getType() == PotionEffectType.SPEED
                                                        && potionEffect.getDuration() > 5*20) {
                                                    found = true;
                                                }
                                            }
                                            if (!found) finalPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5*20, 1),true);
                                            Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, new Runnable() {
                                                public void run() {
                                                    finalPlayer.getWorld().playEffect(finalPlayer.getLocation(), Effect.MOBSPAWNER_FLAMES, 5, 30);
                                                }
                                            }, 30L);
                                        }
                                    }

                                }, 1L);

                            }

                        }

                    }

                }

            }

            ItemStack handItem = player.getInventory().getItemInHand();

            if (handItem.getType() == Material.DIAMOND_SWORD) {

                if (handItem.hasItemMeta() && handItem.getItemMeta().hasLore()) {

                    for (String lore : handItem.getItemMeta().getLore()) {

                        if (lore.equals(ChatColor.AQUA + "forged from his dead body")) {

                            final Player finalPlayer = player;
                            Bukkit.getScheduler().runTaskLater(_plugin, new Runnable() {

                                public void run(){
                                    if (finalPlayer.isOnline()) {
                                        boolean found = false;
                                        for (PotionEffect potionEffect : finalPlayer.getActivePotionEffects()) {
                                            if (potionEffect.getType() == PotionEffectType.INCREASE_DAMAGE
                                                    && potionEffect.getDuration() > 5*20) {
                                                found = true;
                                            }
                                        }
                                        if (!found) finalPlayer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10*20, 0),true);
                                    }
                                }

                            }, 1L);
                            

                        }

                    }

                }

            }

            if (handItem.getType() == Material.BOW) {

                if (handItem.hasItemMeta() && handItem.getItemMeta().hasLore()) {

                    for (String lore : handItem.getItemMeta().getLore()) {

                        if (lore.equals(ChatColor.AQUA + "forged from his dead body")) {

                            final Player finalPlayer = player;
                            Bukkit.getScheduler().runTaskLater(_plugin, new Runnable() {

                                public void run(){
                                    if (finalPlayer.isOnline()) {
                                        boolean found = false;
                                        for (PotionEffect potionEffect : finalPlayer.getActivePotionEffects()) {
                                            if (potionEffect.getType() == PotionEffectType.INVISIBILITY
                                                    && potionEffect.getDuration() > 5*20) {
                                                found = true;
                                            }
                                        }
                                        if (!found) finalPlayer.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10*20, 0),true);
                                    }
                                }

                            }, 1L);
                            

                        }

                    }

                }

            }

        }

    }

}
