package com.mzimu.rpg.data.itemmodule;


import com.mzimu.rpg.data.attribute.ZMAttribute;
import com.mzimu.rpg.data.attribute.tag.*;
import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ZMItemModuleEnum {
    开膛破肚(new ZMItemModuleData(new double[]{15}, 10,"开膛破肚","§3开膛破肚§a",new ZMAttribute[]{new Damage(20,true)})),
    碎骨肉斩(new ZMItemModuleData(new double[]{30,15}, 4,"碎骨肉斩","§3碎骨肉斩§a", new ZMAttribute[]{new Crit(20), new CritDamage(100)})),
    坚壁不摧(new ZMItemModuleData(new double[]{10},4,"坚壁不摧","§3坚壁不摧§a",new ZMAttribute[]{new Defense(10,true)})),
    多重元素(new ZMItemModuleData(new double[]{50},4,"多重元素","§3多重元素§a",new ZMAttribute[]{new SkillMultiple(50)})),
    暂未镶嵌(new ZMItemModuleData(null,-1,"暂未镶嵌","§7暂未镶嵌",new ZMAttribute[]{}));

    private ZMItemModuleData itemModuleData;


    ZMItemModuleEnum(ZMItemModuleData itemModuleData) {
        this.itemModuleData = itemModuleData;
    }

    public ZMItemModuleData getItemModuleData() {
        return itemModuleData;
    }

    public static int getStarOfLore(List<String> lore){
        return lore.indexOf("§a§m-------------§f§l[> 灵能嵌槽 <]§a§m-------------");
    }

    public static int getEndOfLore(List<String> lore){
        return lore.indexOf("§a§m-------------§f§l[> 武器简介 <]§a§m-------------");
    }

    /**
     * 在武器的Lore中获取MOD的数据
     * @param item
     * @return
     */
    public static List<ZMItemModuleData> getItemModuleOfLore(ItemStack item){
        List<String> lore = item.getItemMeta().getLore();
        List<ZMItemModuleData> list = new ArrayList<>();

        int a,b,c;
        a = getStarOfLore(lore)+1;
        b = getEndOfLore(lore);
        c = b-a;
        for(int i=0;i<c;i++){
            list.add(getItemModuleEnum_Lv(lore.get(a+i)));
        }

        return list;
    }

    public static ZMItemModuleEnum getMod(String a){
        switch (a){
            case "开膛破肚":
                return 开膛破肚;
            case "碎骨肉斩":
                return 碎骨肉斩;
            case "坚壁不摧":
                return 坚壁不摧;
            case "多重元素":
                return 多重元素;
            case "暂未镶嵌":
                return 暂未镶嵌;
        }
        throw new IllegalArgumentException("没有找到该MOD");
    }

    public static ZMItemModuleData getItemModuleEnum_Lv(String name) {
        if(name!=null){
            if(name.equals("§7暂未镶嵌")){
                return null;
            }else {
                for (ZMItemModuleEnum itemEnum : ZMItemModuleEnum.values()) {
                    Pattern p = Pattern.compile("(" + itemEnum.getItemModuleData().getLore() + "\\s*)(\\d*)");
                    Matcher m = p.matcher(name);
                    if (m.find()) {
                        int lv = Integer.parseInt(m.group(2));
                        return new ZMItemModuleData(itemEnum, lv);
                    }
                }
            }
        }
        return null;
    }


    public static List<ZMItemModuleData> getItemModuleData(List<String> module){
        List<ZMItemModuleData> iMDList = new ArrayList<>();
        int a,b,c;
        a = module.indexOf("§a§m-------------§f§l[> 灵能嵌槽 <]§a§m-------------")+1;
        b = module.indexOf("§a§m-------------§f§l[> 武器简介 <]§a§m-------------");
        c = b-a;

        for(int i=0;i<c;i++){
            iMDList.add(getItemModuleEnum_Lv(module.get(a+i)));
        }
        return iMDList.size()!=0?iMDList:null;
    }

    /**
     * 检查Lore是否有重复项
     * @param lore 武器的lore
     * @param item 武器
     * @return true 相等 false 不相等
     */
    public static boolean isR(List<String> lore,ItemStack item){
//        int star = ZMItemModuleEnum.getStarOfLore(lore)+1;
//        int end = ZMItemModuleEnum.getEndOfLore(lore);
        String name = item.getItemMeta().getDisplayName();
        for(int i=0;i<lore.size();i++){
            if(lore.get(i).equals(name)){
                return true;
            }
        }
        return false;
    }

}
