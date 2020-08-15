package com.mzimu.rpg.event.trigger.playerAction;

import com.mzimu.rpg.data.ZMPlayItemDate;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnLogin_Quit {

    public static void OnLogin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        List<ItemStack> itemStackList = new ArrayList<>();
        itemStackList.addAll(Arrays.asList(p.getInventory().getArmorContents()));
        itemStackList.add(p.getInventory().getItem(0));
        ZMPlayItemDate.upItem(p,itemStackList);
    }

    public static void OnQuit(PlayerQuitEvent event){
        ZMPlayItemDate.delData(event.getPlayer());
    }

}
