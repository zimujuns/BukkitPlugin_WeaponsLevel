package com.mzimu.rpg.event.gui;

import com.mzimu.rpg.data.itemmodule.ZMItemModuleData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ModuleInstallGui {
    public static final String TITLE = "武器详情界面";
    private Inventory inventory = Bukkit.createInventory(null,9, TITLE);

    public void bulideMod(List<ZMItemModuleData> itemModuleDataList){
        int i=0;
        for(ZMItemModuleData moduleData : itemModuleDataList){
            if(moduleData!=null){
                ItemStack item = moduleData.getItemStack();
                inventory.setItem(i,item);
            }
            i++;
        }
        for(;i<9;i++){
            inventory.setItem(i,new ItemStack(Material.BARRIER));
        }
    }

    public void open(Player player, List<ZMItemModuleData> itemModuleDataList){
        bulideMod(itemModuleDataList);
        player.openInventory(inventory);
    }
}
