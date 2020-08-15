package com.mzimu.rpg.event.customattribute.zmskill;


import cn.mzimu.zmspecialdeath.SpecialDeath;
import cn.mzimu.zmspecialdeath.data.PlayData;
import org.bukkit.entity.Player;

import java.util.Random;

/**
 * 触发坚定后 恢复精神值
 *
 * 概率恢复
 */
public class Spirit_RecoverSpiritEvent {

    public void start(Player p){
        if((new Random().nextInt()*100)>30){
            PlayData specialData = SpecialDeath.playDataMap.get(p.getName());
            specialData.setDeathChance(0.0);
            SpecialDeath.playDataMap.put(p.getName(),specialData);
            p.sendMessage("触发了武器特效 将精神值降为 0");
        }
    }

}
