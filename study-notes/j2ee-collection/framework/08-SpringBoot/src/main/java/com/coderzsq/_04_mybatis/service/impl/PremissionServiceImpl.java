package com.coderzsq._04_mybatis.service.impl;

import com.coderzsq._04_mybatis.domain.Permission;
import com.coderzsq._04_mybatis.mapper.PermissionMapper;
import com.coderzsq._04_mybatis.service.PremissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PremissionServiceImpl implements PremissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> list() {
        return permissionMapper.list();
    }

    @Override
    @Transactional
    public void save(Permission permission) {
        permissionMapper.save(permission);
        // 模拟异常
        int i = 1 / 0;
    }
}
