package com.mzimu.rpg.event;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;

/**
 * 特殊属性
 *   召唤亡灵 杀死这个生物后召唤这个生物 有一定的概率
 *
 */




public class Summon extends EntityZombie{

    public static String is = "FriendlyForces";

    private Player p;
    private static MinecraftKey minecraftKey;

    static{
        // 给我们的自定义实体做一个MinecraftKey
        minecraftKey = new MinecraftKey("Undead"); // minecraft:my_zombie
        // 实体注册
        EntityTypes.d.add(minecraftKey); // 将此key添加至EntityTypes的列表里
        EntityTypes.b.a(54, minecraftKey,Summon.class); // 对其注册
    }

    @SuppressWarnings("unchecked")
    public Summon(Location loc, Player p, LivingEntity entiry) {
        super(((CraftWorld) loc.getWorld()).getHandle());


        this.setCustomName(p.getName()+"的亡灵");
        this.setCustomNameVisible(true);
        this.setPosition(loc.getX(),loc.getY(),loc.getZ());
        float hp = (float) (entiry.getMaxHealth()*0.4);
        this.setHealth(hp>20?hp:20);

        EntityEquipment ee = entiry.getEquipment();
        ItemStack item = CraftItemStack.asNMSCopy(ee.getChestplate());
        this.setSlot(EnumItemSlot.CHEST,item);
        item = CraftItemStack.asNMSCopy(ee.getHelmet());
        this.setSlot(EnumItemSlot.HEAD,item);
        item = CraftItemStack.asNMSCopy(ee.getItemInOffHand());
        this.setSlot(EnumItemSlot.OFFHAND,item);
        item = CraftItemStack.asNMSCopy(ee.getItemInMainHand());
        this.setSlot(EnumItemSlot.MAINHAND,item);
        item = CraftItemStack.asNMSCopy(ee.getBoots());
        this.setSlot(EnumItemSlot.FEET,item);
        item = CraftItemStack.asNMSCopy(ee.getLeggings());
        this.setSlot(EnumItemSlot.LEGS,item);

        this.dropEquipment(false,-1);
        this.dropDeathLoot(false,-1);


        //添加为同一个队伍
        if(!this.world.getScoreboard().addPlayerToTeam(this.getUniqueID().toString(),is)){
            this.world.getScoreboard().createTeam(is);
            this.world.getScoreboard().addPlayerToTeam(this.getUniqueID().toString(),is);
        }else{
            this.world.getScoreboard().addPlayerToTeam(this.getUniqueID().toString(),is);
        }

        //添加标签 让玩家无法攻击这个实体
        this.addScoreboardTag(is);

        this.p = p;
    }


    @Override
    protected void r() {
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalZombieAttack(this, 1.0D, false));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(6, new PathfinderGoalFollowEntity(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));

        this.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(this, EntityMonster.class, true));



    }

    public static boolean isSummon(Entity e){
        if(e.getScoreboardTags().contains(is)){
            return true;
        }
        return false;
    }

//    public class ai extends PathfinderGoal{
//
//        //判断ai任务应该什么时候开始执行
//        @Override
//        public boolean a() {
//            return true;
//        }
//
//
//    }

}


