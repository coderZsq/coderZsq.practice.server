package com.coderzsq._04_mybatis.service;

import com.coderzsq._04_mybatis.domain.Permission;

import java.util.List;

public interface PremissionService {
    public List<Permission> list();

    public void save(Permission permission);
}
