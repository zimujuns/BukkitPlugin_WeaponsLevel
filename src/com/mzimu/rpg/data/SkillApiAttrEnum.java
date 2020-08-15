package com.mzimu.rpg.data;

/**
 * 这里存储的是 在技能设置的过程中
 * 存放的一些值的key 主要功能是想通过
 * 这些Key在PlayerSkill中获取且修改这些数值
 */
public enum SkillApiAttrEnum {
    //增加技能范围
    技能范围("ZMWeapons_Range"),
    //当技能是弹道技能的时候 用来增加数量
    额外数量("ZMWeapons_Multiple");

    private String tab;

    SkillApiAttrEnum(String tab) {
        this.tab = tab;
    }

    public String getTab() {
        return tab;
    }
}
