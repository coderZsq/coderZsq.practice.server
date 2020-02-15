package com.coderZsq._24_shopping.test;

import com.coderZsq._24_shopping.dao.IUserDAO;
import com.coderZsq._24_shopping.dao.impl.UserDAOImpl;
import com.coderZsq._24_shopping.domain.User;
import org.junit.Test;

public class UserDAOTest {
    private IUserDAO dao = new UserDAOImpl();
    @Test
    public void testGetUserByUsername() throws Exception {
        String username = "admin";
        User user = dao.getUserByUsername(username);
        System.out.println(user);
    }
}
