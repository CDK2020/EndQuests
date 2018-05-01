package com.mc.CDK2020.endquests;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import java.lang.reflect.Constructor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import java.util.Date;
import java.util.Random;
import static org.bukkit.ChatColor.*;
import mkremins.fanciful.*;

public class EndQuests extends JavaPlugin {

    private Date _nextDragon = null;
    private FileConfiguration _config = getConfig();
    private int _dragonSpawnTask = -1;
    private BukkitTask _prizeTask = null;
    private Dragon _dragon = null;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new DragonListener(this), this);

        _config = getConfig();
        if (!_config.contains("NextDragonTime")) {
            _nextDragon = new Date(System.currentTimeMillis() + 60*60*1000 + 5*1000);
        } else {
            _nextDragon = new Date(_config.getLong("NextDragonTime"));
        }

        _dragonSpawnTask = getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                long currentTime = System.currentTimeMillis();
                System.out.println(_nextDragon.getTime());
                if (currentTime >= _nextDragon.getTime()) {
                    spawnDragon();
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------" + ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Elite" + ChatColor.AQUA + "MC" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------");
                    Bukkit.broadcastMessage(ChatColor.RED + "THE DRAGONKING HAS SPAWNED" + "\n" ); new FancyMessage("Hover to see drops!")
            .color(GREEN)
            .style(UNDERLINE)
            .tooltip(ChatColor.DARK_RED + "DragonKing's Skull - Helmet:" + "\n" + ChatColor.AQUA + "Effect: Regeneration\n" + ChatColor.DARK_RED + "DragonKing's Wings - Chestplate:" + "\n" + ChatColor.AQUA + "Effect: Jumpboost\n" + 
                    ChatColor.DARK_RED + "DragonKing's Tail - Leggings:" + "\n" + ChatColor.AQUA + "Effect: Fire Resistance\n" + ChatColor.DARK_RED + "DragonKing's Claw's - Boots:" + "\n" + ChatColor.AQUA + "Effect: Speed\n" +
                    ChatColor.DARK_RED + "DragonKing's Fang - Sword:" + "\n" + ChatColor.AQUA + "Effect: Strength\n" + ChatColor.DARK_RED + "DragonKing's Fireball - Bow:" + "\n" + ChatColor.AQUA + "Effect: Invisibility\n"
)
             .send(Bukkit.getOnlinePlayers());
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------------------------------");
                    generateNextDragonTime();
                }
                else if (currentTime + 15*60*1000 >= _nextDragon.getTime()) {
                    //todo: add longer spawn messages... 2h? 12h? 24h?
                    int minutes = (int)((_nextDragon.getTime() - System.currentTimeMillis()) / (60*1000)) + 1;
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------" + ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Elite" + ChatColor.AQUA + "MC" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------");
                    Bukkit.broadcastMessage(ChatColor.RED + "DRAGONKING SPAWNING IN " + (minutes) + " MINUTES!" + "\n" ); new FancyMessage("Hover to see drops!")
            .color(GREEN)
            .style(UNDERLINE)
            .tooltip(ChatColor.DARK_RED + "DragonKing's Skull - Helmet:" + "\n" + ChatColor.AQUA + "Effect: Regeneration\n" + ChatColor.DARK_RED + "DragonKing's Wings - Chestplate:" + "\n" + ChatColor.AQUA + "Effect: Jumpboost\n" + 
                    ChatColor.DARK_RED + "DragonKing's Tail - Leggings:" + "\n" + ChatColor.AQUA + "Effect: Fire Resistance\n" + ChatColor.DARK_RED + "DragonKing's Claw's - Boots:" + "\n" + ChatColor.AQUA + "Effect: Speed\n" +
                    ChatColor.DARK_RED + "DragonKing's Fang - Sword:" + "\n" + ChatColor.AQUA + "Effect: Strength\n" + ChatColor.DARK_RED + "DragonKing's Fireball - Bow:" + "\n" + ChatColor.AQUA + "Effect: Invisibility\n"
)
             .send(Bukkit.getOnlinePlayers());
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------------------------------");
                } else if (currentTime + 1*60*60*1000 >= _nextDragon.getTime() && currentTime + 59*60*1000 <= _nextDragon.getTime()) {
                    int hours = (int)((_nextDragon.getTime() - System.currentTimeMillis()) / (60*60*1000)) + 1;
                    
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------" + ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Elite" + ChatColor.AQUA + "MC" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------");
                    Bukkit.broadcastMessage(ChatColor.RED + "DRAGONKING SPAWNING IN 1 HOUR!" + "\n" ); new FancyMessage("Hover to see drops!")
            .color(GREEN)
            .style(UNDERLINE)
            .tooltip(ChatColor.DARK_RED + "DragonKing's Skull - Helmet:" + "\n" + ChatColor.AQUA + "Effect: Regeneration\n" + ChatColor.DARK_RED + "DragonKing's Wings - Chestplate:" + "\n" + ChatColor.AQUA + "Effect: Jumpboost\n" + 
                    ChatColor.DARK_RED + "DragonKing's Tail - Leggings:" + "\n" + ChatColor.AQUA + "Effect: Fire Resistance\n" + ChatColor.DARK_RED + "DragonKing's Claw's - Boots:" + "\n" + ChatColor.AQUA + "Effect: Speed\n" +
                    ChatColor.DARK_RED + "DragonKing's Fang - Sword:" + "\n" + ChatColor.AQUA + "Effect: Strength\n" + ChatColor.DARK_RED + "DragonKing's Fireball - Bow:" + "\n" + ChatColor.AQUA + "Effect: Invisibility\n"
)
             .send(Bukkit.getOnlinePlayers());
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------------------------------");
                }
            }
        }, 20, 1*60*20);

        _prizeTask = getServer().getScheduler().runTaskTimerAsynchronously(this, new PrizeTask(this), 2*20L, 2*20L);


    }

    @Override
    public void onDisable() {

        Bukkit.getScheduler().cancelTasks(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("dragontime") || cmd.getName().equalsIgnoreCase("nextdragon")) {

            sender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------" + ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Elite" + ChatColor.AQUA + "MC" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------");
            sender.sendMessage(ChatColor.GOLD + "The next DragonKing will spawn on " + ChatColor.AQUA + "" + _nextDragon.toString());
            sender.sendMessage(ChatColor.GOLD + "The current (server) date and time is " +  ChatColor.AQUA + "" + new Date().toString() + "\n" ); new FancyMessage("Hover to see drops!")
            .color(GREEN)
            .style(UNDERLINE)
            .tooltip(ChatColor.DARK_RED + "DragonKing's Skull - Helmet:" + "\n" + ChatColor.AQUA + "Effect: Regeneration\n" + ChatColor.DARK_RED + "DragonKing's Wings - Chestplate:" + "\n" + ChatColor.AQUA + "Effect: Jumpboost\n" + 
                    ChatColor.DARK_RED + "DragonKing's Tail - Leggings:" + "\n" + ChatColor.AQUA + "Effect: Fire Resistance\n" + ChatColor.DARK_RED + "DragonKing's Claw's - Boots:" + "\n" + ChatColor.AQUA + "Effect: Speed\n" +
                    ChatColor.DARK_RED + "DragonKing's Fang - Sword:" + "\n" + ChatColor.AQUA + "Effect: Strength\n" + ChatColor.DARK_RED + "DragonKing's Fireball - Bow:" + "\n" + ChatColor.AQUA + "Effect: Invisibility\n"
)
             .send(sender);
            sender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------------------------------");
            return true;

        } else if (cmd.getName().equalsIgnoreCase("spawndragonhelm")) {

            if (sender.isOp()) {

                if (sender instanceof Player) {

                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonHelm());

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for OPs only!");
                return true;

            }            } else if (cmd.getName().equalsIgnoreCase("spawndragongear")) {

            if (sender.isOp()) {

                if (sender instanceof Player) {

                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonHelm());
                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonChest());
                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonBoots());
                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonPants());
                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonSword());
                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonBow());

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for OPs only!");
                return true;

            }        } else if (cmd.getName().equalsIgnoreCase("spawndragonnow")) {

            if (sender.isOp()) {

                if (sender instanceof Player) {

        World end = Bukkit.getWorld("end");
        _dragon = new Dragon((EnderDragon)end.spawnEntity(new Location(end, 0,85,0), EntityType.ENDER_DRAGON), this);
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------" + ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "Elite" + ChatColor.AQUA + "MC" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------");
new FancyMessage("THE DRAGONKING HAS BEEN SPAWNED MANUALLY!\n")
.color(RED)
.tooltip(ChatColor.DARK_RED + "DragonKing's Skull - Helmet:" + "\n" + ChatColor.AQUA + "Effect: Regeneration\n" + ChatColor.DARK_RED + "DragonKing's Wings - Chestplate:" + "\n" + ChatColor.AQUA + "Effect: Jumpboost\n" + 
                    ChatColor.DARK_RED + "DragonKing's Tail - Leggings:" + "\n" + ChatColor.AQUA + "Effect: Fire Resistance\n" + ChatColor.DARK_RED + "DragonKing's Claw's - Boots:" + "\n" + ChatColor.AQUA + "Effect: Speed\n" +
                    ChatColor.DARK_RED + "DragonKing's Fang - Sword:" + "\n" + ChatColor.AQUA + "Effect: Strength\n" + ChatColor.DARK_RED + "DragonKing's Fireball - Bow:" + "\n" + ChatColor.AQUA + "Effect: Invisibility\n"
)
.then("Hover to see drops!")
            .color(GREEN)
            .style(UNDERLINE)
            .tooltip(ChatColor.DARK_RED + "DragonKing's Skull - Helmet:" + "\n" + ChatColor.AQUA + "Effect: Regeneration\n" + ChatColor.DARK_RED + "DragonKing's Wings - Chestplate:" + "\n" + ChatColor.AQUA + "Effect: Jumpboost\n" + 
                    ChatColor.DARK_RED + "DragonKing's Tail - Leggings:" + "\n" + ChatColor.AQUA + "Effect: Fire Resistance\n" + ChatColor.DARK_RED + "DragonKing's Claw's - Boots:" + "\n" + ChatColor.AQUA + "Effect: Speed\n" +
                    ChatColor.DARK_RED + "DragonKing's Fang - Sword:" + "\n" + ChatColor.AQUA + "Effect: Strength\n" + ChatColor.DARK_RED + "DragonKing's Fireball - Bow:" + "\n" + ChatColor.AQUA + "Effect: Invisibility\n"
)
             .send(Bukkit.getOnlinePlayers());
                    Bukkit.broadcastMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "---------------------------------------------------");
                    

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for OPs only!");
                return true;

            }

        } else if (cmd.getName().equalsIgnoreCase("spawndragonchest")) {

            if (sender.isOp()) {

                if (sender instanceof Player) {

                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonChest());

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for OPs only!");
                return true;

            }
        } else if (cmd.getName().equalsIgnoreCase("socialmedia") || cmd.getName().equalsIgnoreCase("sm")) {

            if (sender.hasPermission("essentials.kit.starter")) {

                if (sender instanceof Player) {

                    sender.sendMessage(ChatColor.RED + "Social media links coming soon!");

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for OPs only!");
                return true;

            }
} else if (cmd.getName().equalsIgnoreCase("sl")) {

            if (sender.hasPermission("stafflist.show")) {

                if (sender instanceof Player) {

                    sender.sendMessage(ChatColor.GOLD + "newhi\n" + ChatColor.AQUA + "Welcome NEW players\n" + ChatColor.GOLD + "staffapp\n" + ChatColor.AQUA + "Tell players how to apply\n" + ChatColor.GOLD + "lookatapp\n" + ChatColor.AQUA + "Tell players not to ask about staff app\n" + ChatColor.GOLD + "hackusate\n" + ChatColor.AQUA + "Tell players not to hackusate\n" + ChatColor.GOLD + "votethx\n" + ChatColor.AQUA + "Thanks players for voting\n" + ChatColor.GOLD + "buypls\n" + ChatColor.AQUA + "Encourage players to donate!\n");

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for staff only!");
                return true;

            }
} else if (cmd.getName().equalsIgnoreCase("apply")) {

    if (sender instanceof Player) {

        if (sender instanceof Player) {

          sender.sendMessage(ChatColor.GOLD + "Elite-Minecraft.com/Staffapp");
        	
        } else {

            sender.sendMessage("This command is for in-game players only!");

        }
        return true;

    } else {

        sender.sendMessage("This command is for staff only!");
        return true;

    }


        } else if (cmd.getName().equalsIgnoreCase("spawndragonpants")) {

            if (sender.isOp()) {

                if (sender instanceof Player) {

                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonPants());

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for OPs only!");
                return true;

            }

        } else if (cmd.getName().equalsIgnoreCase("spawndragonboots")) {

            if (sender.isOp()) {

                if (sender instanceof Player) {

                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonBoots());

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for OPs only!");
                return true;

            }
             } else if (cmd.getName().equalsIgnoreCase("Dragonrewards")) {

            if (sender.hasPermission("essentials.tpa")) {

                if (sender instanceof Player) {

                    sender.sendMessage(ChatColor.DARK_RED + "DragonKing's Skull - Helmet:" + "\n" + ChatColor.AQUA + "Effect: Regeneration\n" + ChatColor.DARK_RED + "DragonKing's Wings - Chestplate:" + "\n" + ChatColor.AQUA + "Effect: Jumpboost\n" + 
                    ChatColor.DARK_RED + "DragonKing's Tail - Leggings:" + "\n" + ChatColor.AQUA + "Effect: Fire Resistance\n" + ChatColor.DARK_RED + "DragonKing's Claw's - Boots:" + "\n" + ChatColor.AQUA + "Effect: Speed\n" +
                    ChatColor.DARK_RED + "DragonKing's Fang - Sword:" + "\n" + ChatColor.AQUA + "Effect: Strength\n" + ChatColor.DARK_RED + "DragonKing's Fireball - Bow:" + "\n" + ChatColor.AQUA + "Effect: Invisibility\n");

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for OPs only!");
                return true;

            }
        } else if (cmd.getName().equalsIgnoreCase("spawndragonbow")) {

            if (sender.isOp()) {

                if (sender instanceof Player) {

                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonBow());

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for OPs only!");
                return true;

            }

        } else if (cmd.getName().equalsIgnoreCase("spawndragonsword")) {

            if (sender.isOp()) {

                if (sender instanceof Player) {

                    ((Player)sender).getInventory().addItem(PrizeUtils.dragonSword());

                } else {

                    sender.sendMessage("This command is for in-game players only!");

                }
                return true;

            } else {

                sender.sendMessage("This command is for OPs only!");
                return true;

            }

        }

        return false;
    }

    public void generateNextDragonTime() {

        //todo: calculate a real time
        // right now it just spawns 3 mins after server launch for testing.

        Date nextDate = new Date(System.currentTimeMillis() + 2*24*60*60*1000);
        int hour = new Random().nextInt(12) + 12;
        int minute = new Random().nextInt(60);

        nextDate.setHours(hour);
        nextDate.setMinutes(minute);

        long newDragonTime = nextDate.getTime();
        _nextDragon = nextDate;
        _config.set("NextDragonTime", newDragonTime);
        saveConfig();

    }

    public void spawnDragon() {

        World end = Bukkit.getWorld("end");
        _dragon = new Dragon((EnderDragon)end.spawnEntity(new Location(end, 0,85,0), EntityType.ENDER_DRAGON), this);

    }




}