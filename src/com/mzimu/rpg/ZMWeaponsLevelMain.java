package com.mzimu.rpg;

import com.mzimu.rpg.command.CommandMain;
import com.mzimu.rpg.data.itemmodule.ZMItemModuleEnum;
import com.mzimu.rpg.data.itemtemplate.ZMItemEnum;
import com.mzimu.rpg.event.thread.WeaponChangeCoolingThread;
import com.mzimu.rpg.trigger.OnItemModuleTrigger;
import com.mzimu.rpg.trigger.OnPlayerActionTrigger;
import com.mzimu.rpg.trigger.OnWeaponsTrigger;
import com.mzimu.rpg.trigger.ZMWeaponsTrigger;
import com.mzimu.rpg.util.ItemUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.minecraft.server.v1_12_R1.EntityZombie;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ZMWeaponsLevelMain extends JavaPlugin {



    private static Map<EntityZombie,Integer> entityZombieIntegerMap = new HashMap<>();
    //用来存储亡灵实体的
    public static final String INVENTORY_HELP = "§f[§a温馨提醒§f]§7已将重复项放入背包中,注意背包为满时,重复项会消失!";
    public static final String INVENTORY_HELP2 = "§f[§a温馨提醒§f]§7存入的灵能不能为复数,已将剩余的灵能放入背包中,注意背包为满时,重复项会消失!";
    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        ZMWeaponsTrigger.register(this,new OnPlayerActionTrigger());
        ZMWeaponsTrigger.register(this,new OnWeaponsTrigger());
        ZMWeaponsTrigger.register(this,new OnItemModuleTrigger());


        //切换武器时的冷却计时器
        BukkitRunnable b2 = new BukkitRunnable() {
            @Override
            public void run() {
                WeaponChangeCoolingThread.main();
            }
        };

        b2.runTaskTimerAsynchronously(this,20l,10l);


        //亡灵计时器
        BukkitRunnable b = new BukkitRunnable() {
            @Override
            public void run() {
                Iterator<Map.Entry<EntityZombie,Integer>> it = entityZombieIntegerMap.entrySet().iterator();
                while (it.hasNext()){
                    Map.Entry<EntityZombie,Integer> entry = it.next();
                    if (entry.getValue() > 0) {
                        entityZombieIntegerMap.put(entry.getKey(),entry.getValue() - 1);
                    } else {
                        entry.getKey().setHealth(0);
                        it.remove();
                    }
                }
            }
        };
        b.runTaskTimerAsynchronously(this,20l,20l);

        this.getCommand("zmwl").setExecutor(new CommandMain());


    }

    public static Map<EntityZombie, Integer> getEntityZombieIntegerMap() {
        return entityZombieIntegerMap;
    }

    public static void sendActionBar(Player player,String s){
        ComponentBuilder builder = new ComponentBuilder(s);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,builder.create());
    }
}
