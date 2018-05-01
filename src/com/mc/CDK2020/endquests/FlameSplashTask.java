package com.mc.CDK2020.endquests;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

/**
 *
 * @author gyroninja
 */
public class FlameSplashTask implements Runnable {

    int _counter = 0;

    EndQuests _plugin;

    Location _loc;

    public FlameSplashTask(EndQuests plugin, Location loc) {

        this._plugin = plugin;

        this._loc = loc;

    }

    public void run() {

        if (_counter < 10) {

            FallingBlock fb = _loc.getWorld().spawnFallingBlock(_loc, Material.FIRE, (byte)1);

            fb.setDropItem(false);

            Vector splash = Vector.getRandom().normalize();

            fb.setVelocity(splash);

            _counter++;

            Bukkit.getScheduler().scheduleSyncDelayedTask(_plugin, this, 1L);
        }
    }
}
