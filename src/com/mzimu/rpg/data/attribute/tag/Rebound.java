package com.mzimu.rpg.data.attribute.tag;


import com.mzimu.rpg.data.attribute.ZMAttribute;

public class Rebound extends ZMAttribute {
    private String attr="反弹几率",attrLore="§e§o反弹几率: §b+";
    /**
     * 判断百分比
     */
    private boolean is = true;

    public Rebound(double number) {
        super.is = is;
        super.attr = attr;
        super.attrLore = attrLore;
        super.number = number;
    }
}
