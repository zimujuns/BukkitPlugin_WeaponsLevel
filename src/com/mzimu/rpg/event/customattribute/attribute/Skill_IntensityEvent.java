package com.mzimu.rpg.event.customattribute.attribute;

import com.mzimu.rpg.data.SkillApiAttrEnum;
import com.mzimu.rpg.data.ZMPlayItemDate;
import com.mzimu.rpg.util.SkillUtil;
import com.sucy.skill.api.player.PlayerSkill;
import com.sucy.skill.dynamic.EffectComponent;
import org.bukkit.entity.Player;

/**
 * 技能强度
 */
public class Skill_IntensityEvent implements SkillApiAttribute {

    @Override
    public double star(EffectComponent ec, PlayerSkill ps, String skillName) {
        Player p = ps.getPlayerData().getPlayer();
        double v = ZMPlayItemDate.getSkillIntensity(p);
        if(v!=0&&ec.getInstanceKey().matches("Skill Level[\\s\\S]*") && SkillUtil.isSkillLevel(ec,ps.getLevel())){
            SkillUtil.forChildren(ec,"ZMWeapons_Intensity",v,p.getName(),skillName);
            return v;
        }
        return v;

    }
}
