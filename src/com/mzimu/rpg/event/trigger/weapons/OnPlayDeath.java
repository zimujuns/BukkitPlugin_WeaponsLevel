package com.mzimu.rpg.event.trigger.weapons;

import com.mzimu.rpg.event.customattribute.zmskill.Spirit_AttackRangeEvent;
import com.mzimu.rpg.event.customattribute.zmskill.Spirit_EffectEvent;
import com.mzimu.rpg.event.customattribute.zmskill.Spirit_RecoverSpiritEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayDeath {

    public static void OnDeath(EntityDamageByEntityEvent e){
        Player p = (Player) e.getEntity();
        ItemStack item = p.getInventory().getItemInMainHand();
        switch (item.getItemMeta().getDisplayName()){
            case "§f[§6§lEnhance§f]§bCthulhu_克鲁苏杀手剑":
                new Spirit_RecoverSpiritEvent().start(p);
                break;
            case "§f[§6§lEnhance§f]§bCthulhu_格拉基启示录":
                new Spirit_AttackRangeEvent().start(p);
                break;
            case "§f[§6§lEnhance§f]§bCthulhu_扭曲的神格":
                new Spirit_EffectEvent().start(p);
                break;
        }
    }
}
