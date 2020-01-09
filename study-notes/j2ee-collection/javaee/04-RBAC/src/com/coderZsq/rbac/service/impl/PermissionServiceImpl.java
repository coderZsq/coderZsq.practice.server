package com.coderZsq.rbac.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.coderZsq.rbac.domain.Permission;
import com.coderZsq.rbac.mapper.PermissionMapper;
import com.coderZsq.rbac.query.QueryObject;
import com.coderZsq.rbac.service.IPermissionService;
import com.coderZsq.rbac.utils.RequiresPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author hesj
 * @Created by 小码哥教育 hesj
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public PageInfo<Permission> query(QueryObject queryObject) {
        Page page=null;
        PageHelper.startPage(queryObject.getCurrentPage(), queryObject.getPageSize());
        List<Permission> permissions = permissionMapper.query(queryObject);
        return new PageInfo<>(permissions);
    }

    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Permission> queryAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public List<Permission> queryByRoleId(Long roleId) {
        return permissionMapper.queryByRoleId(roleId);
    }

    @Override
    @Transactional
    public void reload() {
        //1 查询出所有已经存在的权限信息
        List<Permission> permissions = permissionMapper.selectAll();
        Set<String> permissionExpressions = new HashSet<>();
        for (Permission permission : permissions) {
            permissionExpressions.add(permission.getExpression());
        }
        //2 查看所有的需要检验的表达式, 如果是存在的, 则跳过, 如果不存在, 就新增
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RestController.class);
        Collection<Object> controllerBeans = beans.values();
        for (Object controllerBean : controllerBeans) {
            Method[] declaredMethods = controllerBean.getClass().getDeclaredMethods();
            String className = controllerBean.getClass().getSimpleName();
            for (Method method : declaredMethods) {
                if(method.isAnnotationPresent(RequiresPermission.class)){
                    RequiresPermission annotation = method.getAnnotation(RequiresPermission.class);
                    String name = annotation.value();
                    String expression=className+":"+method.getName();
                    if(!permissionExpressions.contains(expression)){
                        Permission permission = new Permission();
                        permission.setExpression(expression);
                        permission.setName(name);
                        permissionMapper.insert(permission);
                    }
                }
            }
        }
    }
}
