package com.mzimu.rpg.event.trigger.playerAction.Inventory;

import com.mzimu.rpg.data.itemmodule.ZMItemModuleData;
import com.mzimu.rpg.data.itemmodule.ZMItemModuleEnum;
import com.mzimu.rpg.data.itemtemplate.ZMItemEnum;
import com.mzimu.rpg.event.gui.ItemLvUpGui;
import com.mzimu.rpg.event.gui.ModuleInstallGui;
import com.mzimu.rpg.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class OnClick {

    public static void main(InventoryClickEvent e){
        Inventory inventory = e.getClickedInventory();
        ItemStack cur = e.getCurrentItem();
        //点击的不是空的 且 不是空气
        Player p = (Player) e.getWhoClicked();
        if (inventory != null && e.getCurrentItem().getType() != Material.AIR) {
            //对应类型对应事件
            switch (e.getClick()) {
                case MIDDLE:
                case CREATIVE:
                    if(cur==null){
                        return;
                    }
                    //打开MOD界面
                    if(p.getInventory().getItemInMainHand().isSimilar(cur) && ItemUtil.isItemPrefix(cur)){
                        List<ZMItemModuleData> itemModuleDataList = ZMItemModuleEnum.getItemModuleData(cur.getItemMeta().getLore());
                        new ModuleInstallGui().open(p,itemModuleDataList);
                    }
                    break;
                default:
                    switch (inventory.getTitle()){
                        case ItemLvUpGui.TITLE:
                            //取消在升级武器界面的点击书本事件
                            if(cur.getType() == Material.BOOK || e.getAction() == InventoryAction.HOTBAR_SWAP){
                                e.setCancelled(true);
                                updataItem(inventory,ItemLvUpGui.getEMaterial(inventory),ItemLvUpGui.getEItem(inventory),p);
                            }
                            break;
                        case ModuleInstallGui.TITLE:
                            //取消在MOD界面的点击屏障事件
                            if(cur.getType() == Material.BARRIER || e.getAction() == InventoryAction.HOTBAR_SWAP){
                                e.setCancelled(true);
                            }
                            break;
                    }
                    break;
            }
        }
    }

    private static void updataItem(Inventory iy, ItemStack iE,ItemStack iW, Player player){
        if(ItemUtil.isItemPrefix(iW) && iE.isSimilar(ZMItemEnum.getEMaterial())){

            int lv = ItemUtil.getLevel(iW);
            ItemStack[] item = ItemUtil.itemLvUpdate(player,ItemLvUpGui.getEItem(iy),iE,lv);
            iy.setItem(0,
                    item[0]);
            iy.setItem(8,
                    item[1]);
        }
    }
}
