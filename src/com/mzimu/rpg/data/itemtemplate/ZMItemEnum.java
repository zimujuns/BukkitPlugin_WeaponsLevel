package com.mzimu.rpg.data.itemtemplate;

import com.mzimu.rpg.data.attribute.ZMAttribute;
import com.mzimu.rpg.data.attribute.tag.*;
import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ZMItemEnum {
    Natalia_纳塔利_Enhance(
            new ZMItemData(
                    2
                    ,0
                    ,new ZMAttribute[]{
                            new Damage(46),
                            new Crit(25),
                            new CritDamage(100)
                    }
            )
            ,"§f[§6§lEnhance§f]§bNatalia-纳塔利"
    ),

    Macaber_暗黑镰刀_Enhance(
            new ZMItemData(
                    2,
                    0,
                    new ZMAttribute[]{
                            new Damage(38),
                            new Crit(30),
                            new CritDamage(80)
                    }
            )
            ,"§f[§6§lEnhance§f]§bMacaber_暗黑镰刀"

    ),
    Cthulhu_克鲁苏杀手剑(
            new ZMItemData(
                    2,
                    0,
                    new ZMAttribute[]{
                            new Damage(42),
                            new Crit(30),
                            new CritDamage(110)
                    }
            )
            ,"§f[§6§lEnhance§f]§bCthulhu_克鲁苏杀手剑"
    ),

    Cthulhu_格拉基启示录(
            new ZMItemData(
                    2,
                    0,
                    new ZMAttribute[]{
                            new Damage(42),
                            new Crit(30),
                            new CritDamage(110)
                    }
            )
            ,"§f[§6§lEnhance§f]§bCthulhu_格拉基启示录"
    ),
    Cthulhu_扭曲的神格(
            new ZMItemData(
                    2,
                    0,
                    new ZMAttribute[]{
                            new Damage(42),
                            new Crit(30),
                            new CritDamage(110)
                    }
            )
            ,"§f[§6§lEnhance§f]§bCthulhu_扭曲的神格"
    ),
    Larissa_拉蕊莎重甲_Enhance(
            new ZMItemData(
                    2
                    ,0
                    ,new ZMAttribute[]{
                            new Defense(130),
                            new Rebound(15),
                            new ReboundPercentage(20)
                    }
            )
            ,"§f[§6§lEnhance§f]§bLarissa_拉蕊莎重甲"
    );


    private ZMItemData ZMItemData;
    public final String itemName;
    ZMItemEnum(ZMItemData ZMItemData, String itemName) {
        this.ZMItemData = ZMItemData;
        this.itemName = itemName;
    }

    public ZMItemData getItemEnumData(){
        return ZMItemData;
    }

    public static ZMItemEnum getEnum(ItemStack item){
        return getEnum(item.getItemMeta().getDisplayName());
    }

    public static ZMItemEnum getEnum(String itemName){
        for(ZMItemEnum ZMItemEnum : ZMItemEnum.values()){
            if(itemName.equals(ZMItemEnum.itemName)){
                return ZMItemEnum;
            }
        }
        return null;
    }

    public static ItemStack getEMaterial(){
        return MythicMobs.inst().getItemManager().getItemStack("Enhance_IRON");
    }

    public static String getLvLoreString(){
        return "§a§o等级:§7 ";
    }

    public static String getExpString(int a, int b){
        return a+"/"+b;
    }
    
    public static int getExp(ItemStack item){
        return getExp(item.getItemMeta().getLore());
    }

    private static int getExp(List<String> lore) {
        return getExp(lore.get(lore.size()-1));
    }

    private static int getExp(String s) {
        Pattern p = Pattern.compile("(\\d*)");
        Matcher m = p.matcher(s);
        if(m.find()){
            return Integer.parseInt(m.group(1));
        }
        return 0;
    }

}
