package com.mzimu.rpg.data.attribute.tag;


import com.mzimu.rpg.data.attribute.ZMAttribute;

public class SkillIntensity extends ZMAttribute {

    private String attr="技能强度",attrLore="§c§o技能强度: §b";
    private double number=0;
    /**
     * 判断百分比
     */
    private boolean is = true;

    public SkillIntensity(double number) {
        super.is = is;
        super.attr = attr;
        super.attrLore = attrLore;
        super.number = number;
    }

}
