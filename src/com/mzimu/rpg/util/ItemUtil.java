package com.mzimu.rpg.util;

import com.mzimu.rpg.data.itemtemplate.ZMItemEnum;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemUtil {

    /**
     * 再获取武器的等级
     * @param item
     * @return
     */
    public static Integer getLevel(ItemStack item){
        if(!isItemPrefix(item)){
            return null;
        }
        int listLvIndex = item.getItemMeta().getLore().size() - 4;
//        Pattern p = Pattern.compile("(§\\d?\\w?)*([^\\x00-\\xff]*:\\s*)(§\\d?\\w?)*(\\d*)");
        Pattern p = Pattern.compile("(\\S*)(\\s?)(\\d*)");
        Matcher m = p.matcher(item.getItemMeta().getLore().get(listLvIndex));
        if(m.find()){
            return m.group(3).isEmpty()?0:Integer.parseInt(m.group(3));
        }
        return 0;
    }

    /**
     * 判断是不是对应的武器
     * @param item
     * @return
     */
    public static boolean isItemPrefix(ItemStack item){
        try{
            String itemName =item.getItemMeta().getDisplayName();
            //判断前缀
            if(itemName.matches(".*Enhance.*")){
                return true;
            }
            return false;
        }catch (NullPointerException nullP){
            return false;
        }

    }

    public static ItemStack[] itemLvUpdate(Player player, ItemStack eItem, ItemStack iron, int lv) {
        ItemStack[] item = new ItemStack[2];
        int ironAmount = iron.getAmount();
        int exp = ZMItemEnum.getExp(eItem)+ironAmount,b=0;
        int maxExp;
        while (true){
            switch (lv/10){
                case 0:
                    maxExp = 2;
                    break;
                case 1:
                    maxExp = 6;
                    break;
                case 2:
                    maxExp = 12;
                    break;
                case 3:
                    maxExp = 24;
                    break;
                default:
                case 4:
                    maxExp = 30;
                    break;

            }
            if(lv>=99){
                ironAmount=exp;
                exp=0;
                break;
            }else if(exp>=maxExp){
                exp-=maxExp;
                lv++;
                b++;
            }else{
                ironAmount=0;
                break;
            }

        }
        player.sendMessage("升级完成,提升了" + b + "装备等级");
        String sLv = ZMItemEnum.getLvLoreString()+(lv);
        String sExp = ZMItemEnum.getExpString(exp,maxExp);
        ItemMeta im = eItem.getItemMeta();
        List<String> lore = im.getLore();
        lore.set(lore.size()-2,sLv);
        lore.set(lore.size()-1,sExp);
        im.setLore(lore);
        eItem.setItemMeta(im);
        iron.setAmount(ironAmount);
        item[0]=eItem;
        item[1]=iron;
        return item;
    }
}
