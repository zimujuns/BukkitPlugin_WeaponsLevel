package com.mzimu.rpg.data.attribute.tag;

import com.mzimu.rpg.data.attribute.ZMAttribute;

public class SkillMultiple  extends ZMAttribute {

    private String attr="抛射物倍率",attrLore="§c§o抛射物倍率: §b";
    private double number=0;
    /**
     * 判断百分比
     */
    private boolean is = true;

    public SkillMultiple(double number) {
        super.is = is;
        super.attr = attr;
        super.attrLore = attrLore;
        super.number = number;
    }

}
