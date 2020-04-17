package com.coderzsq.demo;

import com.coderzsq._04_mybatis.AppConfig;
import com.coderzsq._04_mybatis.domain.Permission;
import com.coderzsq._04_mybatis.mapper.PermissionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class MapperTest {
    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void testList() throws Exception {
        List<Permission> list = permissionMapper.list();
        System.out.println("list = " + list);
    }

    @Test
    public void testSave() throws Exception {
        Permission permission = new Permission();
        permission.setExpression("ooxx");
        permission.setName("test");
        permissionMapper.save(permission);
    }
}
