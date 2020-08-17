package com.sq.service.impl;

import com.sq.service.PersonService;

public class PersonServiceImpl2 implements PersonService {
    @Override
    public boolean remove(Integer id) {
        System.out.println("PersonServiceImpl2 - remove: " + id);
        return false;
    }
}
