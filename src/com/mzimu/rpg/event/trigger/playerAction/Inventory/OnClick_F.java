package com.mzimu.rpg.event.trigger.playerAction.Inventory;

import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class OnClick_F {

    public static void main(PlayerSwapHandItemsEvent e){
        e.setCancelled(true);
    }

}
