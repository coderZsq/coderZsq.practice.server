package com.sq.proxy;

import com.sq.service.SkillService;

public class SkillServiceProxy implements SkillService {
    private SkillService target;

    public void setTarget(SkillService target) {
        this.target = target;
    }

    @Override
    public boolean save(Object skill) {
        System.out.println("SkillServiceProxy - save - ");
        return target.save(skill);
    }
}
