package com.mzimu.rpg.event.customattribute.zmskill;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Iterator;

/**
 * 特殊属性
 *    范围攻击  在杀死一个生物的时候触发 不会对玩家造成效果 但会对亡灵造成效果
 */
public class AttackRangeEvent {
    public void AttackRange_Death(EntityDeathEvent e) {
        if(!(e.getEntity() instanceof LivingEntity) || e.getEntity() instanceof Player){
            return;
        }
        Iterator<Entity> iterator = e.getEntity().getNearbyEntities(5,5,5).iterator();
        while (iterator.hasNext()){
            Entity entity = iterator.next();
            if(entity instanceof LivingEntity){
                //若是玩家或者NPC就跳出
                if(entity instanceof Player){
                    continue;
                }
                LivingEntity livingEntity =  (LivingEntity) entity;
                livingEntity.damage(300);
                effect(entity);
            }
        }

        return;
    }

    public void effect(Entity e){
        e.getWorld().spawnParticle(Particle.EXPLOSION_LARGE,e.getLocation(),1,1,1,1);
    }
}
