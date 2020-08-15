package com.mzimu.rpg.event.trigger.playerAction.Inventory;

import com.mzimu.rpg.ZMWeaponsLevelMain;
import com.mzimu.rpg.data.ZMPlayItemDate;
import com.mzimu.rpg.data.itemmodule.ZMItemModuleEnum;
import com.mzimu.rpg.event.gui.ItemLvUpGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnClose {

    public static void main(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        switch (e.getInventory().getTitle()){
            case "武器详情界面":
                ItemStack playHand = e.getPlayer().getInventory().getItemInMainHand();
                List<String> lore = playHand.getItemMeta().getLore();
                List<String> loreNew = new ArrayList<>();
                int a = ZMItemModuleEnum.getStarOfLore(lore)+1;
                int b = a;
                //这里写更新lore的操作 为了避免装备重复MOD的问题 需要使用新的Lore操作 操作完后再把武器上的Lore的对应内容替换成新的Lore中的内容
                for(ItemStack item : e.getInventory().getContents()){
                    try {
                        if(item.getType()!= Material.BARRIER){
                            if(ZMItemModuleEnum.isR(loreNew,item)){
                                p.sendMessage(ZMWeaponsLevelMain.INVENTORY_HELP);
                                p.getInventory().addItem(item);
                                loreNew.add(ZMItemModuleEnum.暂未镶嵌.getItemModuleData().getLore());
                            }else{
                                if(item.getAmount()>1) {
                                    item.setAmount(item.getAmount() - 1);
                                    p.getInventory().addItem(item);
                                    p.sendMessage(ZMWeaponsLevelMain.INVENTORY_HELP2);
                                }
                                loreNew.add(item.getItemMeta().getDisplayName());
                            }
                        }else{
                            break;
                        }
                    }catch (NullPointerException nE){
                        loreNew.add(ZMItemModuleEnum.暂未镶嵌.getItemModuleData().getLore());
                    }
                    a++;
                }
                for(int i=0;b<a;b++,i++){
                    lore.set(b,loreNew.get(i));
                }
                ItemMeta im = playHand.getItemMeta();
                im.setLore(lore);
                playHand.setItemMeta(im);
                p.getInventory().setItemInMainHand(playHand);
                break;
            case ItemLvUpGui.TITLE:
                ItemStack v1 = e.getInventory().getItem(0),v2 = e.getInventory().getItem(8);
                if(v1 != null){
                    p.getInventory().addItem(e.getInventory().getItem(0));
                }
                if(v2 != null){
                    p.getInventory().addItem(e.getInventory().getItem(8));
                }

        }
//        ItemStack[] itemStacks = new ItemStack[5];
        List<ItemStack> itemStackList = new ArrayList<>();
        itemStackList.addAll(Arrays.asList(p.getInventory().getArmorContents()));
        itemStackList.add(p.getInventory().getItem(0));
        ZMPlayItemDate.upItem(p,itemStackList);
    }

}
