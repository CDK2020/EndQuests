package com.mc.CDK2020.endquests;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.EnderDragon;


import java.util.Random;

public class DragonListener implements Listener {

    private EndQuests _plugin;

    public DragonListener(EndQuests plugin) {

        _plugin = plugin;

    }

    @EventHandler
    public void onFireballHit(ProjectileHitEvent event) {

        if (event.getEntity().getType() == EntityType.FIREBALL) {

            Fireball fireball = (Fireball)event.getEntity();
            if (fireball.hasMetadata("eq")) {

                for (Entity entity : event.getEntity().getNearbyEntities(5,5,5)) {

                    if (entity instanceof Player) {

                        Player player = (Player)entity;
                        if (!player.isDead()) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 5 * 20, 0));
                            player.damage(10.0);
                        }

                    }

                }

                Bukkit.getScheduler().runTask(_plugin, new FlameSplashTask(_plugin, fireball.getLocation()));


            }

        }

    }

    @EventHandler
    public void onEntityDeath(EntityChangeBlockEvent event) {

        if (event.getEntityType() == EntityType.FALLING_BLOCK) {


            if (event.getTo() == Material.FIRE) {

                for (Entity e : event.getEntity().getNearbyEntities(5, 5, 5)) {

                    if (e instanceof Player) {

                        Player p = (Player) e;
                        if (!p.isDead()) {
                            if (p.getFireTicks() < 200) p.setFireTicks(200);
                            p.damage(5.0);
                        }

                    }

                }

                event.setCancelled(true);
            }
        }

    }

    @EventHandler
    public void onEnderdragonHit(EntityDamageByEntityEvent event) {

        if (event.getDamager().getType() == EntityType.ENDER_DRAGON && event.getDamager().getWorld().equals(Bukkit.getWorld("end"))) {
            event.setDamage(event.getDamage() * 3);
            if (event.getEntity() instanceof Player) {
                Player player = (Player)event.getEntity();
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 90*20, 0));
            }
        }

    }

    @EventHandler
    public void onEnderdragonTakesHit(EntityDamageByEntityEvent event) {

        if (event.getEntity().getType() == EntityType.ENDER_DRAGON && event.getDamager().getWorld().equals(Bukkit.getWorld("end"))) {
            event.setDamage(event.getDamage() / 2);
        }

    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    if (player.isOp()){
    player.setGameMode(GameMode.CREATIVE);
    try {
        Thread.sleep(3000);                 //1000 milliseconds is one second.
    } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
    }
    player.sendMessage(ChatColor.DARK_RED + "Welcome back, Sir");
    player.getWorld().playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 5, 30);
    } else
      player.getWorld().playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 5, 30);
    }

    @EventHandler
    public void onEnderdragonDeath(EntityDeathEvent event) {

        if ((event.getEntity().getType() == EntityType.ENDER_DRAGON) && (event.getEntity().getWorld() == Bukkit.getWorld("end"))) {

            EnderDragon dragon = (EnderDragon)event.getEntity();
            Bukkit.broadcastMessage(ChatColor.AQUA + "THE SUPERDRAGON WAS KILLED BY " + ChatColor.RED + dragon.getKiller().getDisplayName().toUpperCase() + "!");

            for (int i=0; i < 10; i++) {
                Firework firework = (Firework) dragon.getWorld().spawnEntity(dragon.getLocation(), EntityType.FIREWORK);
                FireworkMeta meta = firework.getFireworkMeta();
                FireworkEffect fe = FireworkEffect.builder().with(FireworkEffect.Type.STAR).withColor(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW).withFade(Color.WHITE).withTrail().withFlicker().build();
                meta.addEffect(fe);
                meta.setPower(2);
                firework.setFireworkMeta(meta);
            }

            event.setDroppedExp(event.getDroppedExp() * 5);

            int dropNumber = (new Random().nextInt(6));
            if (dropNumber < 1) event.getDrops().add(PrizeUtils.dragonHelm());
            else if (dropNumber < 2) event.getDrops().add(PrizeUtils.dragonChest());
            else if (dropNumber < 3) event.getDrops().add(PrizeUtils.dragonPants());
            else if (dropNumber < 4) event.getDrops().add(PrizeUtils.dragonBoots());
            else if (dropNumber < 5) event.getDrops().add(PrizeUtils.dragonSword());
            else event.getDrops().add(PrizeUtils.dragonBow());


        }
        }
        


    



}
