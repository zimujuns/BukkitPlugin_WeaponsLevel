package com.mzimu.rpg.event.trigger.weapons;

import com.mzimu.rpg.event.customattribute.zmskill.AttackRangeEvent;
import com.mzimu.rpg.event.customattribute.zmskill.SummonUndeadEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class OnKillEntity {

    public static void OnKill(EntityDeathEvent deathEvent, Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();
        switch (item.getItemMeta().getDisplayName()){
            case "§f[§6§lEnhance§f]§bNatalia-纳塔利":
                new AttackRangeEvent().AttackRange_Death(deathEvent);
                break;
            case "§f[§6§lEnhance§f]§bMacaber_暗黑镰刀":
                new SummonUndeadEvent(p,deathEvent.getEntity());
                break;

        }
    }

}
