package com.mzimu.rpg.trigger;

import com.mzimu.rpg.event.Summon;
import com.mzimu.rpg.event.trigger.weapons.OnKillEntity;
import com.mzimu.rpg.event.trigger.weapons.OnPlayDeath;
import com.mzimu.rpg.util.ItemUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnWeaponsTrigger implements Listener,ZMWeaponsTrigger {

    @EventHandler
    public void onDeathEvent(EntityDamageByEntityEvent damageE){
        if(damageE.getDamager() instanceof Player){
            Player player = (Player) damageE.getDamager();
            if(Summon.isSummon(damageE.getEntity())){
                damageE.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void OnKickEvent(EntityDeathEvent deathEvent){
        if(deathEvent.getEntity().getKiller()!=null && deathEvent.getEntity().getKiller().getInventory().getItemInMainHand()!=null){
            if(ItemUtil.isItemPrefix(deathEvent.getEntity().getKiller().getInventory().getItemInMainHand())){
                OnKillEntity.OnKill(deathEvent,deathEvent.getEntity().getKiller());
            }
        }

    }

}
