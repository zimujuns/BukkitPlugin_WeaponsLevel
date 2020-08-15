package com.mzimu.rpg.event.customattribute.attribute;

import com.mzimu.rpg.data.ZMPlayItemDate;
import com.mzimu.rpg.util.SkillUtil;
import com.sucy.skill.api.player.PlayerSkill;
import com.sucy.skill.dynamic.EffectComponent;
import org.bukkit.entity.Player;

/**
 * 投掷物倍率
 *
 *
 */
public class Skill_MultipleEvent implements SkillApiAttribute {
    @Override
    public double star(EffectComponent ec, PlayerSkill ps, String skillName) {
        Player p = ps.getPlayerData().getPlayer();
        double v = ZMPlayItemDate.getSkillMultiple(p);
        if(v!=0&&ec.getInstanceKey().matches("Skill Level[\\s\\S]*") && SkillUtil.isSkillLevel(ec,ps.getLevel())){
            SkillUtil.forChildren(ec,"ZMWeapons_Multiple",v,p.getName(),skillName);
            return v;
        }
        return v;
    }
}
