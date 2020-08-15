package com.mzimu.rpg.event.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemLvUpGui {
    private static ItemStack item = getItem();
    public static final String TITLE = "强化界面";
    private Inventory inventory = Bukkit.createInventory(null,9, "强化界面");

    public void builde(){
        for(int i=1;i<8;i++){
            inventory.setItem(i,item);
        }
    }

    public void open(Player p){
        builde();
        p.openInventory(inventory);
    }

    /**
     * 初始化界面
     * @return
     */
    public static ItemStack getItem(){
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta im = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("§f§l<=====================");
        lore.add("   §6§l将武器放置左方");
        lore.add("   §6§l将材料放置右方");
        lore.add("§f§l=====================>");
        lore.add("§f§l<<§a§l点我直接进行强化§f§l>>");
        im.setLore(lore);
        item.setItemMeta(im);
        return item;

    }

    public static ItemStack getEItem(Inventory iy){
        return iy.getItem(0);
    }

    public static ItemStack getEMaterial(Inventory iy){
        return iy.getItem(8);
    }

}
