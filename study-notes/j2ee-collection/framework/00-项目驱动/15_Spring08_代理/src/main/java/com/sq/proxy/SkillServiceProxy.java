package com.sq.proxy;

import com.sq.service.SkillService;

public class SkillServiceProxy extends SkillService {
    private SkillService target;

    public void setTarget(SkillService target) {
        this.target = target;
    }

    @Override
    public boolean save(Object skill) {
        System.out.println("SkillServiceProxy - 1");
        boolean result = target.save(skill);
        System.out.println("SkillServiceProxy - 2");
        return result;
    }
}
