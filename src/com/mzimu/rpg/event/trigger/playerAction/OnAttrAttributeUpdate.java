package com.mzimu.rpg.event.trigger.playerAction;

import com.mzimu.rpg.data.ZMPlayItemDate;
import org.bukkit.entity.Player;
import org.serverct.ersha.jd.attribute.AttributeManager;

import java.util.Map;

public class OnAttrAttributeUpdate {



    /**
     *
     * @param p 玩家
     */
    public static void main(Player p) {
//        if(p.getInventory().getHeldItemSlot() == 0){
            Map<String,Double> vm = ZMPlayItemDate.getNumMap().get(p.getName());
            if(vm != null){
                for(Map.Entry<String,Double> entry : AttributeManager.getAttrData(p).getAttributeValue().entrySet()){
//                event.setDamage(onCount(p,event.getFinalDamage()));
                    if(vm.containsKey(entry.getKey())) {
                        if (entry.getKey().matches("物理伤害\\S*")) {
                            entry.setValue(ZMPlayItemDate.getDamageNum(p) + entry.getValue());
                        }else if(entry.getKey().matches("物理防御\\S*")){
                            entry.setValue(ZMPlayItemDate.getDefenseNum(p) + entry.getValue());
                        } else {
                            entry.setValue(entry.getValue() + entry.getValue() * vm.get(entry.getKey()));
                        }
                    }
                }
            }

//        }

    }
}
