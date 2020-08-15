package com.mzimu.rpg.event.customattribute.zmskill;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * 触发坚定后 恢复精神值
 *
 * 赋予药水效果 挖掘速度提高
 */
public class Spirit_EffectEvent {

    public void start(Player p){
        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,10,3));
    }

}
