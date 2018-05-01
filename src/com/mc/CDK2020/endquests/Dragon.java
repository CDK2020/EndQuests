package com.mc.CDK2020.endquests;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EnderDragon;

public class Dragon {

    public int _dragonID;
    public EndQuests _plugin;

    public Dragon(EnderDragon DragonEntity, EndQuests plugin) {

        _dragonID = DragonEntity.getEntityId();
        _plugin = plugin;

        DragonEntity.setCustomName(ChatColor.RED + "Dragon King");

        Bukkit.getScheduler().runTaskTimer(plugin, new DragonTargetTask(_dragonID, _plugin), 100, 60);

    }

    //todo: add cancel tasks method

}
