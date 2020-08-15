package com.mzimu.rpg.event.customattribute.zmskill;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Iterator;

/**
 * 在进入坚定状态下 造成 范围性的 击退
 */
public class Spirit_AttackRangeEvent {

    public void start(Player p){
        Iterator<Entity> iterator = p.getNearbyEntities(5,5,5).iterator();
        while (iterator.hasNext()){
            Entity entity = iterator.next();
            if(entity instanceof LivingEntity){
                //若是玩家或者NPC就跳出
                if(entity instanceof Player){
                    continue;
                }
                entity.setVelocity(new Vector().zero().subtract(entity.getLocation().getDirection()).multiply(5));
                effect(p.getLocation(),5,120);
            }
        }
    }

    public void effect(Location loc, double radii, int particleAmount){
        for(double angle = 0; angle < 360D; angle+=(360D/particleAmount)){
            double x = Math.sin(Math.toRadians(angle)) * radii,
                    z = Math.cos(Math.toRadians(angle)) * radii;
            loc.getWorld().playEffect(loc.clone().add(x, 0, z), Effect.FLAME, 0);
        }
    }

}
