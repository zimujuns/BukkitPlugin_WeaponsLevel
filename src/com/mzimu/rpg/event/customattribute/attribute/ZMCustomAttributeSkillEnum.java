package com.mzimu.rpg.event.customattribute.attribute;

public enum ZMCustomAttributeSkillEnum {

    Skill_Intensity(new Skill_IntensityEvent()),
    Skill_Multiple(new Skill_MultipleEvent());

    private SkillApiAttribute attr;

    ZMCustomAttributeSkillEnum(SkillApiAttribute attr) {
        this.attr = attr;
    }

    public SkillApiAttribute getAttr() {
        return attr;
    }
}
