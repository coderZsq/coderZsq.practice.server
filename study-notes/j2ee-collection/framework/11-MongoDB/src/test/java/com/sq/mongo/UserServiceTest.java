package com.sq.mongo;

import com.sq.mongo.entity.User;
import com.sq.mongo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private IUserService userService;
    @Autowired
    private MongoTemplate template;

    @Test
    void save() {
        User user = new User();
        user.setName("dafei");
        user.setAge(18);
        user.setHobby(Arrays.asList("java", "c"));
        userService.save(user);
    }

    @Test
    void update() {
        //全量更新
        User user = new User();
        user.setId("xxxxxId");
        user.setName("dafei");
        user.setAge(18);
        user.setHobby(Arrays.asList("java", "c"));
        userService.update(user);
    }

    @Test
    void update2() {
        //部分更新
        template.updateMulti(
                Query.query(Criteria.where("_id").is("xxxxId")),
                Update.update("name", "dafei2"), User.class);
    }

    @Test
    void delete() {
        userService.delete("xxxxid");
    }

    @Test
    void get() {
        System.out.println(userService.get("xxxxId"));
    }

    @Test
    void list() {
        userService.list().forEach(System.out::print);
    }
}
