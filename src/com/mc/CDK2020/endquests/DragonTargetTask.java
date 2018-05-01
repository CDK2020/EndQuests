package com.mc.CDK2020.endquests;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class DragonTargetTask implements Runnable {

    public int _dragonID;
    public EndQuests _plugin;
    public static int counter = 0;

    public DragonTargetTask(int enderDragonID, EndQuests plugin) {

        _dragonID = enderDragonID;
        _plugin = plugin;

    }

    public void run() {

        EnderDragon dragon = null;

        for(Entity possible : Bukkit.getWorld("end").getEntitiesByClass(EnderDragon.class)) {

            dragon = (EnderDragon)possible;

        }
        if (dragon == null) return;
        if (dragon.isDead()) return;

        for (Entity target : dragon.getNearbyEntities(45, 45, 45)) {

            if (target instanceof Player && target.isOp()) {

                Location sourceLocation = dragon.getEyeLocation();
                sourceLocation = sourceLocation.add(0, -15, 0);
                Vector fireDirection = target.getLocation().toVector().subtract(sourceLocation.toVector());

                fireDirection.multiply(2);

                if (counter == 0) System.out.println("FIRE!");
                if (counter > 10) {
                    counter = 0;
                    return;
                }
                Fireball fireball = (Fireball)dragon.getWorld().spawnEntity(sourceLocation, EntityType.FIREBALL);
                fireball.setMetadata("eq", new FixedMetadataValue(_plugin, 2));
                fireball.setDirection(fireDirection);
                Bukkit.getScheduler().runTaskLater(_plugin, this, 1);
                counter++;

                break;

            }
        }
    }
}