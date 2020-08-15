package com.mzimu.rpg.trigger;


import com.mzimu.rpg.ZMWeaponsLevelMain;
import com.mzimu.rpg.event.thread.WeaponChangeCoolingThread;
import com.mzimu.rpg.event.trigger.playerAction.Inventory.OnClick;
import com.mzimu.rpg.event.trigger.playerAction.Inventory.OnClick_F;
import com.mzimu.rpg.event.trigger.playerAction.Inventory.OnClose;
import com.mzimu.rpg.event.trigger.playerAction.Inventory.OnDrag;
import com.mzimu.rpg.event.trigger.playerAction.OnAttrAttributeUpdate;
import com.mzimu.rpg.event.trigger.playerAction.OnLogin_Quit;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.serverct.ersha.jd.event.AttrAttributeUpdateEvent;


public class OnPlayerActionTrigger implements Listener,ZMWeaponsTrigger {

    @EventHandler(
            priority = EventPriority.LOWEST
    )
    public void onUpdateAttr(AttrAttributeUpdateEvent event){
        if(event.getEntity() instanceof Player && ((Player) event.getEntity()).getInventory().getHeldItemSlot()==0){
            OnAttrAttributeUpdate.main((Player) event.getEntity());
        }
        return;

    }

    @EventHandler
    public void OnDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && WeaponChangeCoolingThread.isPlay((Player)e.getDamager())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void OnLogin(PlayerJoinEvent e){
        OnLogin_Quit.OnLogin(e);
    }


    @EventHandler
    public void OnQuit(PlayerQuitEvent e){
        OnLogin_Quit.OnQuit(e);
    }


    @EventHandler
    public void OnInventoryDrag(InventoryDragEvent e){
        OnDrag.main(e);
    }

    @EventHandler
    public void OnSwapHand(PlayerSwapHandItemsEvent e){
        OnClick_F.main(e);
    }


    @EventHandler
    public void OnInventoryClose(InventoryCloseEvent e){
        OnClose.main(e);
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e){
        OnClick.main(e);
    }


    /**
     * 将更换物品的玩家 加入切换冷却的线程
     * @param e
     */
    @EventHandler
    public void OnInventoryMove(PlayerItemHeldEvent e){
        WeaponChangeCoolingThread.putMap(e.getPlayer());
    }

}
