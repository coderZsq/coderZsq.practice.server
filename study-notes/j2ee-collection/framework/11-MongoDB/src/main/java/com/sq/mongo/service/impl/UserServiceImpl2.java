package com.sq.mongo.service.impl;

import com.sq.mongo.entity.User;
import com.sq.mongo.repository.UserRepository;
import com.sq.mongo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Service
public class UserServiceImpl2 implements IUserService {
    @Autowired
    private UserRepository repository;

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void update(User user) {
        repository.save(user);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public User get(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<User> list() {
        return repository.findAll();
    }
}
