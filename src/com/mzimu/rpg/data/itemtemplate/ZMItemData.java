package com.mzimu.rpg.data.itemtemplate;


import com.mzimu.rpg.data.attribute.ZMAttribute;
import com.mzimu.rpg.data.attribute.ZMAttributeData;

import java.util.HashMap;
import java.util.Map;

public class ZMItemData extends ZMAttributeData {
    private ZMAttribute[] attributeArray;
    private int lv;
    private double valueUp;

    public ZMItemData(double valueUp, int lv, ZMAttribute... attributeArray) {
        this.valueUp = valueUp;
        this.lv = lv;
        this.attributeArray = attributeArray;
    }


    public ZMAttribute[] getAttributeArray() {
        return attributeArray;
    }

    public Map<String,Double> getAttrNumberMap(){
        Map<String,Double> attrMap = new HashMap<>();
        for(ZMAttribute attr : attributeArray){
            attrMap.put(attr.getAttr(),attr.getNumber());
        }
        return attrMap;
    }

    public double getValueUp() {
        return valueUp;
    }

}
