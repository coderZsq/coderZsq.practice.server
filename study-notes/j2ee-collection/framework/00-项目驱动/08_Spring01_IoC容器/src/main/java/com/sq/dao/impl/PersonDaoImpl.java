package com.sq.dao.impl;

import com.sq.dao.PersonDao;

public class PersonDaoImpl implements PersonDao {
    @Override
    public boolean remove(Integer id) {
        System.out.println("PersonDaoImpl ------ remove: " + id);
        return false;
    }
}
