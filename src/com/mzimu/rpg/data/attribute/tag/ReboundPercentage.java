package com.mzimu.rpg.data.attribute.tag;

import com.mzimu.rpg.data.attribute.ZMAttribute;

public class ReboundPercentage extends ZMAttribute {
    private String attr="反弹倍率",attrLore="§e§o反弹倍率: §b+";
    /**
     * 判断百分比
     */
    private boolean is = true;

    public ReboundPercentage(double number) {
        super.is = is;
        super.attr = attr;
        super.attrLore = attrLore;
        super.number = number;
    }
}
