package com.mzimu.rpg.data.itemmodule;


import com.mzimu.rpg.data.attribute.ZMAttribute;
import com.mzimu.rpg.data.attribute.ZMAttributeData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个是MOD的数据结构
 */

public class ZMItemModuleData extends ZMAttributeData {
    private ZMAttribute[] ZMAttributeArray;
    private double[] valueUp;
    private int maxLevel;
    private int lv;
    private String lore;
    private ZMItemModuleEnum type;


    public ZMItemModuleData(ZMItemModuleEnum itemEnum, int lv) {
        this(itemEnum.getItemModuleData().getAttributeArrays(),itemEnum.getItemModuleData().getValueUp(),itemEnum.getItemModuleData().getMaxLevel(),lv,itemEnum,itemEnum.getItemModuleData().getLore());
    }

    public ZMItemModuleData(double[] valueUp, int maxLevel, String name, String lore, ZMAttribute... ZMAttributeArray) {
        this(ZMAttributeArray, valueUp, maxLevel, 0,name,lore);
    }

    public ZMItemModuleData(ZMAttribute[] ZMAttributeArray, double[] valueUp, int maxLevel, int lv, Object type, String lore) {
        this.ZMAttributeArray = ZMAttributeArray;
        this.valueUp = valueUp;
        this.maxLevel = maxLevel;
        this.lv = lv;
        this.lore = lore;
        if(type instanceof String){
            this.type = ZMItemModuleEnum.getMod((String) type);
        }else{
            this.type = (ZMItemModuleEnum) type;
        }

    }

    public ZMAttribute[] getAttributeArrays() {
        return ZMAttributeArray;
    }

    public double[] getValueUp() {
        return valueUp;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getLv() {
        return lv;
    }

    public String getLore() {
        return lore;
    }

    public ZMItemModuleEnum getType() {
        return type;
    }

    /**
     * 这里的attr 是直接把lore中的词条带入进来即可
     * @param attr
     * @return
     */
    public ZMAttribute getAttribute(String attr) {
        return this.getAttribute(ZMAttributeArray,attr,true);
    }

    /**
     * 已测试 无问题
     * @return
     */
    public ItemStack getItemStack() {
        if(type!= ZMItemModuleEnum.暂未镶嵌){
            ItemStack itemStack = new ItemStack(Material.BOOK);
            ItemMeta im = itemStack.getItemMeta();
            im.setDisplayName(lore+" "+lv+"§f§m/§a"+maxLevel);
            List<String> lore = new ArrayList<>();
            lore.add("§f§m---------------");
            for(int i = 0; i< ZMAttributeArray.length; i++){
                ZMAttribute ZMAttribute = ZMAttributeArray[i];
                String content = ZMAttribute.getAttrLoreString() + (ZMAttribute.getNumber() + (valueUp[i] * lv) + "%") ;
                lore.add(content);
            }
            lore.add("§f§m---------------");
            im.setLore(lore);
            itemStack.setItemMeta(im);
            return itemStack;
        }
        return new ItemStack(Material.AIR);
    }
}
