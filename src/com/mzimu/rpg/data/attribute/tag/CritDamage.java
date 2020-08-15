package com.mzimu.rpg.data.attribute.tag;

import com.mzimu.rpg.data.attribute.ZMAttribute;

public class CritDamage extends ZMAttribute {
    private String attr="暴击伤害",attrLore="§e§o暴击伤害: §b+";
    /**
     * 判断百分比
     */
    private boolean is = true;

    public CritDamage(double number) {
        super.is = is;
        super.attr = attr;
        super.attrLore = attrLore;
        super.number = number;
    }

}
