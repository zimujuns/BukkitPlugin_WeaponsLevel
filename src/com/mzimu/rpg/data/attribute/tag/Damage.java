package com.mzimu.rpg.data.attribute.tag;

import com.mzimu.rpg.data.attribute.ZMAttribute;

public class Damage extends ZMAttribute {
    private String attr="物理伤害",attrLore="§c§o武器伤害: §b",attrLoreA="§c§o武器伤害[百分比]: §b";
    private double number=0;
    /**
     * 判断百分比
     */
    private boolean is = false;

    public Damage(double number) {
        super.is = is;
        super.attr = attr;
        super.attrLore = attrLore;
        super.number = number;
    }

    public Damage(double number,Boolean iss) {
        super.is = iss;
        super.attr = attr;
        super.attrLore = attrLoreA;
        super.number = number;
    }

}
