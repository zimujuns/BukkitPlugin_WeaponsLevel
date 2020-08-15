package com.mzimu.rpg.event.customattribute.zmskill;


import com.mzimu.rpg.ZMWeaponsLevelMain;
import com.mzimu.rpg.event.Summon;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scoreboard.Team;

import java.util.Random;

/**
 * 击杀怪物 有概率 召唤相同装备的一个实体
 */
public class SummonUndeadEvent {
    public static Team zombieTeam = Bukkit.getScoreboardManager().getNewScoreboard().registerNewTeam("SummonUndead");

    public SummonUndeadEvent(Player p, LivingEntity entity) {
        if(isRandom()){
            Summon s = new Summon(p.getLocation(),p,entity);
            ((CraftWorld)p.getLocation().getWorld()).getHandle().addEntity(s, CreatureSpawnEvent.SpawnReason.CUSTOM);
            ZMWeaponsLevelMain.getEntityZombieIntegerMap().put(s,8);
            zombieTeam.addEntry(String.valueOf(s.getUniqueID()));
        }
    }

    public boolean isRandom(){
        int n =  new Random().nextInt(100);
        if(n<30) {
            return true;
        }
        return false;
    }
}
