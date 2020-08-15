package com.mzimu.rpg.data.attribute;

public class ZMAttributeData {
    public ZMAttribute getAttribute(ZMAttribute[] ZMAttributeArray, String attr, boolean isLoreString) {
        if(isLoreString){
            for(int i = 0; i< ZMAttributeArray.length; i++){
                if(attr.matches(ZMAttributeArray[i].getAttrLoreString()+".*")){
                    return ZMAttributeArray[i];
                }
            }
        }else{
            for(int i = 0; i< ZMAttributeArray.length; i++){
                if(attr.equals(ZMAttributeArray[i].getAttr())){
                    return ZMAttributeArray[i];
                }
            }
        }
        return null;
    }
}
