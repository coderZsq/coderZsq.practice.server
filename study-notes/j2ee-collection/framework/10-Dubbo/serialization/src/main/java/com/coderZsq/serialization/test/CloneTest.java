package com.coderZsq.serialization.test;

import com.coderZsq.serialization.domain.GirlFriend;
import com.coderZsq.serialization.domain.User;
import org.junit.jupiter.api.Test;

public class CloneTest {
    @Test
    public void testClone() throws Exception {
        // 1. 创建一个User对象
        User jack = new User();
        jack.setName("jack");
        jack.setAge(18);

        GirlFriend lucy = new GirlFriend();
        lucy.setName("lucy");
        // jack.setGf(lucy);

        // User john = jack.clone();
        // 深度克隆
        User john = jack.deepClone();
        john.setAge(21);
        john.setName("john");
        // john.getGf().setName("mary");

        System.out.println("john = " + john);
        System.out.println("jack = " + jack);
    }
}
