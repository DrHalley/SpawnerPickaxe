package com.drhalley.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("spp").setExecutor(new SpawnerPickaxe(this));

    }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if (e.getBlock().getType().toString().equals("SPAWNER") && p.getItemInHand().getItemMeta() != null){

            String itemname = p.getItemInHand().getItemMeta().getDisplayName();
            if(e.getPlayer().getInventory().getItemInHand().getType().toString().equals("DIAMOND_PICKAXE")){

                if(!itemname.equals(getConfig().get("pickaxe-name"))){
                    e.setCancelled(true);
                    p.sendMessage("You can't break this spawner with this pickaxe");
                }
            }else{
                p.sendMessage("You can't break this spawner with this item");
            }
        }else if(p.getItemInHand().getItemMeta() == null){
            e.setCancelled(true);
            p.sendMessage("How did you do that?");
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
