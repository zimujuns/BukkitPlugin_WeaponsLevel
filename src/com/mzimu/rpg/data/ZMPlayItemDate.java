package com.mzimu.rpg.data;

import com.mzimu.rpg.data.attribute.ZMAttribute;
import com.mzimu.rpg.data.attribute.tag.Damage;
import com.mzimu.rpg.data.attribute.tag.Defense;
import com.mzimu.rpg.data.attribute.tag.SkillIntensity;
import com.mzimu.rpg.data.attribute.tag.SkillMultiple;
import com.mzimu.rpg.data.itemmodule.ZMItemModuleData;
import com.mzimu.rpg.data.itemmodule.ZMItemModuleEnum;
import com.mzimu.rpg.data.itemtemplate.ZMItemEnum;
import com.mzimu.rpg.util.ItemUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.serverct.ersha.jd.attribute.AttributeManager;

import java.util.*;

public class ZMPlayItemDate {
    private static Set<String> attrSet = AttributeManager.IIiIiIIIII.keySet();
    private static HashMap<String, data> itemDataMap = new HashMap<>();
    private static HashMap<String, Map<String, Double>> numMap = new HashMap<>();
    private static HashMap<String, Map<String, Double>> customAttMap = new HashMap<>();


    /**
     * 更新Map 操作
     * @param player
     * @param itemArray
     */
    public static void upItem(Player player,List<ItemStack> itemArray){
        Iterator<ItemStack> iterator = itemArray.iterator();
        while (iterator.hasNext()){
            ItemStack item = iterator.next();
            Integer Itemlv = ItemUtil.getLevel(item);
            if(Itemlv==null){
                continue;
            }

            itemDataMap.put(
                    player.getName(),
                    new data(
                            ZMItemEnum.getEnum(item),
                            Itemlv,
                            ZMItemModuleEnum.getItemModuleOfLore(item),
                            player
                    )
            );
            ZMPlayItemDate.data d = ZMPlayItemDate.getData(player);
            if(d!=null){
                try{
                    Map<String,Double> m = numMap.get(player.getName());
                    m.putAll(onCount(d));
                    numMap.put(player.getName(),m);
                }catch (NullPointerException nE){
                    numMap.put(player.getName(),onCount(d));
                }
            }else{
                return;
            }
        }

    }

    /**
     * 删除玩家武器数据
     * @param player
     */
    public static void delData(Player player) {
        itemDataMap.remove(player);
    }

    /**
     * 获取玩家武器数据
     * @param p 目标玩家
     * @return
     */
    public static data getData(Player p) {
        if (itemDataMap.containsKey(p.getName())) {
            return itemDataMap.get(p.getName());
        } else {
            return null;
        }

    }

    /**
     * 获取玩家物理伤害 [是经过计算MOD的物理伤害]
     * @param p 目标玩家
     * @return
     */
    public static Double getDamageNum(Player p) {
        if(itemDataMap.containsKey(p.getName())){
            return numMap.get(p.getName()).get(Damage.getAttr());
        }
        return 0.0;
    }
    /**
     * 获取玩家物理防御
     */
    public static Double getDefenseNum(Player p){
        if(itemDataMap.containsKey(p.getName())){
            return numMap.get(p.getName()).get(Defense.getAttr());
        }
        return 0.0;
    }


    /**
     * 获取自定义属性 - 技能消耗
     * @param p
     * @return
     */
    public static Double getSkillIntensity(Player p) {
        return customAttMap.containsKey(p.getName())?(customAttMap.get(p.getName()).containsKey(SkillIntensity.getAttr())?customAttMap.get(p.getName()).get(SkillIntensity.getAttr()):0.0):0.0;
    }

    /**
     * 获取自定义属性 - 抛射物倍率
     * @param p
     * @return
     */
    public static Double getSkillMultiple(Player p) {
        return customAttMap.containsKey(p.getName())?(customAttMap.get(p.getName()).containsKey(SkillMultiple.getAttr())?customAttMap.get(p.getName()).get(SkillMultiple.getAttr()):0.0):0.0;
    }


    /**
     * 获取自定义属性
     */
    public static HashMap<String, Map<String, Double>> getCustomAttMap() {
        return customAttMap;
    }

    /**
     * 获取所有修改过的原有属性
     * @return
     */
    public static HashMap<String, Map<String, Double>> getNumMap() {
        return numMap;
    }

    /**
     * 这是额外的伤害事件
     *   作用于
     *      升级提升的伤害
     *      MOD提升的伤害
     *   都在这里计算
     * @return
     */
    public static Map<String, Double> onCount(ZMPlayItemDate.data d){
        Map<String,Double> varMap1 = new HashMap<>();
        Map<String,Double> varMap2 = new HashMap<>();
        double vUp = d.getZmItemEnum().getItemEnumData().getValueUp(),var2;
        String attrStr = d.getZmItemEnum().getItemEnumData().getAttributeArray()[0].getAttr();
        switch (d.getItemLv()/10){
            case 1:
                vUp+=1;
                break;
            case 2:
                vUp+=1.2;
                break;
            case 3:
                vUp+=1.5;
                break;
            default:
                vUp+=2;
                break;
        }
        //计算等级增幅后的伤害
        var2 = vUp * d.getItemLv();
        //总的伤害
        varMap1.put(attrStr,var2);
        varMap1.put(attrStr + "[1]",var2);

        for(ZMItemModuleData moduleDate : d.getModuleList()){
            try{
                for(int i=0;i<moduleDate.getAttributeArrays().length;i++){
                    ZMAttribute zmAttribute = moduleDate.getAttributeArrays()[i];
                    String key = zmAttribute.getAttr();
                    double v = moduleDate.getValueUp()[i];
                    double moduleV = v*moduleDate.getLv()+zmAttribute.getNumber();
                    //判断是否是AttrPlus原有的属性
                    if(attrSet.contains(key)){
                        if(zmAttribute instanceof Damage || zmAttribute instanceof Defense){
                            //等级提升的量 + 原有的量
                            if(zmAttribute.isIs()){
                                if(varMap1.containsKey(key)){
                                    varMap1.put(key,var2 * (moduleV/100.0) + varMap1.get(key));
                                    varMap1.put(key+"[1]",var2 * (moduleV/100.0) + varMap1.get(key+"[1]"));
                                }else{
                                    varMap1.put(key,var2 * (moduleV/100.0));
                                    varMap1.put(key+"[1]",var2 * (moduleV/100.0));
                                }
                            }else{
                                if(varMap1.containsKey(key)){
                                    varMap1.put(key,moduleV+varMap1.get(key));
                                    varMap1.put(key+"[1]",moduleV+varMap1.get(key+"[1]"));
                                }else{
                                    varMap1.put(key,moduleV);
                                    varMap1.put(key+"[1]",moduleV);
                                }

                            }
                        }else{
                            //关于其他属性的百分比判断 被弃用 直接全用百分比
//                            if(zmAttribute.isIs()){
                            varMap1.put(key,(varMap1.get(key) + v)/100);
//                            }else{
//                                var1.put(key,var1.get(key) + v);
//                            }

                        }
                    }else{
                        //没有就是AttrPlus的属性就是ZM自定义的属性 既存放到另外一个Map中
                        varMap2.put(key,moduleV/100);
                    }

                }
            }catch (NullPointerException nE){
                continue;
            }
        }
        customAttMap.put(d.getP().getName(),varMap2);
        return varMap1;
    }
//    public static double onCount(Player p){
//        ZMPlayItemDate.data d = ZMPlayItemDate.getData(p);
//        if(d!=null){
//            double var1,var2;
//            double vUp = d.getZmItemEnum().getItemEnumData().getValueUp();
//            switch (d.getItemLv() % 10){
//                case 1:
//                    vUp+=1;
//                    break;
//                case 2:
//                    vUp+=1.2;
//                    break;
//                case 3:
//                    vUp+=1.5;
//                    break;
//                default:
//                    vUp+=2;
//                    break;
//            }
//            //计算等级增幅后的伤害
//            var1 = vUp * d.getItemLv();
//            //总的伤害
//
//            for(ZMItemModuleData moduleDate : d.getModuleList()){
//                try{
//                    for(int i=0;i<moduleDate.getAttributeArrays().length;i++){
//                        ZMAttribute zmAttribute = moduleDate.getAttributeArrays()[i];
//                        if(zmAttribute instanceof Damage){
//                            double v = moduleDate.getValueUp()[i];
//                            if(zmAttribute.isIs()){
//                                var1 += var1 * ((v*moduleDate.getLv()+zmAttribute.getNumber())/100.0);
//                            }else{
//                                var1 += v*moduleDate.getLv()+zmAttribute.getNumber();
//                            }
//                        }
//                    }
//                }catch (NullPointerException nE){
//                    continue;
//                }
//            }
//            return var1;
//        }else {
//            return 0.0;
//        }
//    }






    /**
     * 玩家武器数据类
     */
    public static class data {
        private Player p;
        private ZMItemEnum zmItemEnum;
        private Integer itemLv;
        private List<ZMItemModuleData> moduleList;


        public data(ZMItemEnum zmItemEnum, Integer itemLv, List<ZMItemModuleData> moduleList,Player p) {
            this.zmItemEnum = zmItemEnum;
            this.itemLv = itemLv;
            this.p = p;
            this.moduleList = moduleList;
        }

        public ZMItemEnum getZmItemEnum() {
            return zmItemEnum;
        }

        public Integer getItemLv() {
            return itemLv;
        }

        public List<ZMItemModuleData> getModuleList() {
            return moduleList;
        }

        public Player getP() {
            return p;
        }
    }


}
