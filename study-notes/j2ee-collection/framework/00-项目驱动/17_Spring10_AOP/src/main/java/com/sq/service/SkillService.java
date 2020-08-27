package com.sq.service;

import com.sq.annotation.Log;

public class SkillService {
    private int age;

    @Log
    public boolean save(Object skill) {
        System.out.println("SkillService - save");
        return false;
    }
}
