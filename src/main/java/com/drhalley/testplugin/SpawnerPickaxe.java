package com.drhalley.testplugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpawnerPickaxe implements CommandExecutor {
    private TestPlugin testPlugin;
    public SpawnerPickaxe(TestPlugin testPlugin){
        this.testPlugin = testPlugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 2 && args[0] == "set") {
                String name = args[1];
                testPlugin.getConfig().set("pickaxe-name", name);
                p.sendMessage("Pickaxe name successfully changed");
            } else if (args.length == 2 && args[0] == "get") {
                String pickaxename = testPlugin.getConfig().get("pickaxe-name").toString();
                ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
                ItemMeta pickaxeMeta =pickaxe.getItemMeta();
                pickaxeMeta.setDisplayName(pickaxename);
                p.getInventory().addItem(pickaxe);
            }
        }else{
            sender.sendMessage("This command cant use from console");
        }
        return false;
    }
}
