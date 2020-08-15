package com.mzimu.rpg.event.trigger.playerAction.Inventory;

import com.mzimu.rpg.event.gui.ItemLvUpGui;
import com.mzimu.rpg.event.gui.ModuleInstallGui;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnDrag {

    public static void main(InventoryDragEvent e){
        switch (e.getInventory().getTitle()){
            case ItemLvUpGui
                    .TITLE:
            case ModuleInstallGui
                    .TITLE:
                e.setCancelled(true);
                break;
        }

    }

}
