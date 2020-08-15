package com.mzimu.rpg.data.attribute.tag;

import com.mzimu.rpg.data.attribute.ZMAttribute;

public class Defense extends ZMAttribute {
    private String attr="物理防御",attrLore="§c§o物理防御[百分比]: §b";
    private double number=0;
    /**
     * 判断百分比
     */
    private boolean is = false;

    public Defense(double number) {
        super.is = is;
        super.attr = attr;
        super.attrLore = attrLore;
        super.number = number;
    }

    public Defense(double number,Boolean iss) {
        super.is = iss;
        super.attr = attr;
        super.attrLore = attrLore;
        super.number = number;
    }

}
