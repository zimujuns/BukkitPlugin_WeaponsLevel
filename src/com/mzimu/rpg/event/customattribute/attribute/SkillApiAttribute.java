package com.mzimu.rpg.event.customattribute.attribute;

import com.sucy.skill.api.player.PlayerSkill;
import com.sucy.skill.dynamic.EffectComponent;
import org.bukkit.entity.Player;

/**
 *  在SkillApi中的格式
 *  必须要有一个等级判断
 *   对应的等级拥有对应的值
 *   所以需要判断等级
 */
public interface SkillApiAttribute {

    double star(EffectComponent ec, PlayerSkill ps, String skillName);

}
