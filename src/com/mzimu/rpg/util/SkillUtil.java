package com.mzimu.rpg.util;

import com.sucy.skill.dynamic.EffectComponent;
import org.bukkit.entity.Player;

import java.util.*;

public class SkillUtil {

    private static List<String> list = new ArrayList<>();
    private static Map<String,Map<String,Map<String,Double>>> map = new HashMap<>();

    /**
     * 将技能修改
     * @param ec
     * @param att
     * @param v
     * @param playName
     * @return
     */
    public static double forChildren(EffectComponent ec, String att, double v, String playName, String skillName){
        double vN =0.0 ;
        for(EffectComponent ecc : ec.children){
            if(ecc.getInstanceKey().matches("Value[\\s\\S]*")){
                if(ecc.getSettings().getString("key").equals(att)) {
                    try{
                        vN=getKeyNumForEc(map.get(playName).get(skillName).getOrDefault(att+"Base",1.0),map.get(playName).get(skillName).getOrDefault(att+"Scale",0.0),v);
                    }catch (NullPointerException nE){
                        Map<String,Double> map1 = new HashMap<>();
                        Map<String,Map<String,Double>> map2 = new HashMap<>();
                        map1.put(att+"Base",ecc.getSettings().getBase("value"));
                        map1.put(att+"Scale",ecc.getSettings().getScale("value"));
                        map2.put(skillName,map1);
                        map.put(playName,map2);
                        vN=getKeyNumForEc(ecc.getSettings().getBase("value"),ecc.getSettings().getScale("value"),v);
//                        nE.printStackTrace();
                    }
                    //当有小数的时候进行概率判断 小数为5 就有百分50的概率 以此类推
                    int chang = (int) ((vN*10)%10);
                    if(chang!=0 && new Random().nextInt(10) < chang-1){
                        vN++;
                    }
                    ecc.getSettings().setBase("value",(int)vN);
                    ecc.getSettings().setScale("value",0);
                    return vN;
                }
            }else{
                vN = forChildren(ecc,att,v,playName,skillName);
            }
        }
        return vN;
    }


    /**
     * 用来计算最终的结果 倍率 * 基础值 + 基础值
     * @param v
     * @return
     */
    public static double getKeyNumForEc(double base,double scale,double v){
        double n = base + scale;
        return n*v+n;
    }

    public static boolean isSkillLevel(EffectComponent ec,int lv){
        String min="min-level",max="max-level";
        if(ec.getSettings().getInt(max)>=lv && ec.getSettings().getInt(min)<=lv){
            return true;
        }
        return false;
    }


    public static boolean isList(Player p) {
        return list.contains(p.getName());
    }

    public static void addList(Player p){
        list.add(p.getName());
    }

    public static void delList(Player p) {
        list.remove(p.getName());
    }

}
