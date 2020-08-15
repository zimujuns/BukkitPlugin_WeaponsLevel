package com.mzimu.rpg.event.thread;

import com.mzimu.rpg.ZMWeaponsLevelMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WeaponChangeCoolingThread {
    private static ConcurrentHashMap<Player,Integer> coolingPlayMap = new ConcurrentHashMap<>();
    private static int time = 8;

    public static void main() {
        Iterator<Map.Entry<Player,Integer>> iterator = coolingPlayMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Player,Integer> m = iterator.next();
            int a = m.getValue();
            if(a-- > 0){
                m.setValue(a);
                String actionMsg = "§f§l[§6§l武器切换冷却条§f§l] [§a§m";
                for(int i=0;i<time-a;i++){
                    actionMsg +=" ------ ";
                }
                actionMsg +="§7§m";
                for(int i=0;i<a;i++){
                    actionMsg +=" ------ ";
                }
                actionMsg += "§f§l]";
                ZMWeaponsLevelMain.sendActionBar(m.getKey(),actionMsg);
            }else{
                System.out.println("删除了");
                iterator.remove();
            }
        }
    }

    public static void removeMap(Player player){
        coolingPlayMap.remove(player);
    }

    public static void putMap(Player player){
        coolingPlayMap.put(player,time);
//        ZMWeaponsLevelMain.sendActionBar(player,"§f[§6§l温馨提醒§f]§7§l切换武器会造成冷却，期间无法造成伤害§f[§6§l温馨提醒§f]");
    }

    public static boolean isPlay(Player name) {
        return coolingPlayMap.containsKey(name);
    }
}
