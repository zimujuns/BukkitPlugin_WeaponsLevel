package com.mzimu.rpg.data.attribute.tag;

import com.mzimu.rpg.data.attribute.ZMAttribute;

public class Crit extends ZMAttribute {
    private String attr="暴击几率",attrLore="§e§o暴击几率: §b+";
    private double number=0;
    /**
     * 判断百分比
     */
    private boolean is = true;

    public Crit(double number) {
        super.is = is;
        super.attr = attr;
        super.attrLore = attrLore;
        super.number = number;
    }
}
