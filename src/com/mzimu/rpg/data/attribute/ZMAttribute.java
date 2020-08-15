package com.mzimu.rpg.data.attribute;

public class ZMAttribute {
    public static String attr;
    public String attrLore;
    public double number=0;
    /**
     * 判断百分比
     */
    public boolean is = true;

    public static String getAttr(){
        return attr;
    }
    public double getNumber(){
        return number;
    }
    public String getAttrLoreString(){
        return attrLore;
    }

    public boolean isIs(){
        return is;
    }

    public void setNumber(double number){
        this.number = number;
    }

}
