package com.mzimu.rpg.event.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ModuleLvUpGui {
    private Inventory inventory = Bukkit.createInventory(null,9, TITLE);
    public static final String TITLE = "灵能详情界面";

    public void builde(){

    }

    public void main(Player p){
        builde();
        p.openInventory(inventory);
    }

}
