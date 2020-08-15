package com.mzimu.rpg.trigger;

import com.mzimu.rpg.data.ZMPlayItemDate;
import com.mzimu.rpg.event.customattribute.attribute.ZMCustomAttributeSkillEnum;
import com.mzimu.rpg.util.SkillUtil;
import com.sucy.skill.api.event.PlayerCastSkillEvent;
import com.sucy.skill.api.event.PlayerSkillCastFailedEvent;
import com.sucy.skill.api.player.PlayerSkill;
import com.sucy.skill.api.skills.Skill;
import com.sucy.skill.dynamic.DynamicSkill;
import com.sucy.skill.dynamic.EffectComponent;
import com.sucy.skill.dynamic.trigger.TriggerComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mzimu.rpg.util.SkillUtil.*;

public class OnItemModuleTrigger implements Listener,ZMWeaponsTrigger  {

    @EventHandler
    public void OnSkill(PlayerCastSkillEvent e) throws NoSuchFieldException, IllegalAccessException {
        if(e.getSkill().getData().canCast()){
            if(!isList(e.getPlayer())){
                e.setCancelled(true);
                PlayerSkill ps = e.getSkill();
                DynamicSkill skill = ((DynamicSkill)ps.getData());
                //通过反射去获取私有值
                Field field = skill.getClass().getDeclaredField("castTrigger");
                //修改权限 用来访问私有的方法或属性
                field.setAccessible(true);
                TriggerComponent tc = (TriggerComponent)field.get(skill);
                //获取到后 遍历触发器数组
                for(EffectComponent ec : tc.children){
                    /*循环技能词条枚举类 来实行修改的方法
                    待优化
                    优化想法
                        将数组改为List当返回的值为0时，就在List中删除这个方法
                        不为0的既保存。
                        解释:因为当返回的值为0的时候 玩家手上的武器是没有该属性的，所以不必要进行修改
                        当返回值不为0时。
                        在循环中也判断到对应的值后，修改且删除自己
                        暂未想好
                     */
                    for(ZMCustomAttributeSkillEnum zmcase : ZMCustomAttributeSkillEnum.values()){
                        zmcase.getAttr().star(ec,ps,skill.getName());
                    }
                }
                ps.getPlayerData().cast(new PlayerSkill(e.getPlayerData(),skill,e.getPlayerData().getMainClass()));
                addList(e.getPlayer());
            }else{
                delList(e.getPlayer());
            }
        }

//        e.getPlayerData().setMana(e.getSkill().getManaCost() * (v/100));
    }








}
