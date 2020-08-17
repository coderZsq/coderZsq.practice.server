package com.sq.service.impl;

import com.sq.dao.PersonDao;
import com.sq.service.PersonService;

public class PersonServiceImpl implements PersonService {
    // private PersonDao dao = GeneralFactory.get("personDao");
    private PersonDao dao;

    public void setDao(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean remove(Integer id) {
        return dao.remove(id);
    }
}
