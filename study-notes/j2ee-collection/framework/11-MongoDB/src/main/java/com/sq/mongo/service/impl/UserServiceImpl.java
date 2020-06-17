package com.sq.mongo.service.impl;

import com.sq.mongo.entity.User;
import com.sq.mongo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private MongoTemplate template;

    @Override
    public void save(User user) {
        template.save(user, "users");
    }

    @Override
    public void update(User user) {
        template.save(user, "users");
    }

    @Override
    public void delete(String id) {
        User user = new User();
        user.setId(id);
        template.remove(user);
    }

    @Override
    public User get(String id) {
        return template.findById(id, User.class);
    }

    @Override
    public List<User> list() {
        return template.findAll(User.class);
    }
}
