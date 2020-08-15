package com.mzimu.rpg.data.attribute.tag;


import com.mzimu.rpg.data.attribute.ZMAttribute;

public class AttackRange extends ZMAttribute {
    private String attr="攻击延展",attrLore="§e§o攻击延展: §b+";
    /**
     * 判断百分比
     */
    private boolean is = true;

    public AttackRange(double number) {
        super.is = is;
        super.attr = attr;
        super.attrLore = attrLore;
        super.number = number;
    }
}
