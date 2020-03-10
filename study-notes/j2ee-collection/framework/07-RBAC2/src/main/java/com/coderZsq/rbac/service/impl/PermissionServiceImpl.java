package com.coderZsq.rbac.service.impl;

import com.coderZsq.rbac.domain.Permission;
import com.coderZsq.rbac.qo.PageResult;
import com.coderZsq.rbac.qo.QueryObject;
import com.coderZsq.rbac.util.RequiredPermission;
import com.coderZsq.rbac.mapper.PermissionMapper;
import com.coderZsq.rbac.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper mapper;//NullPointException

    @Override
    public void saveOrUpdate(Permission permission) {
        if (permission.getId() == null) {
            mapper.insert(permission);
        } else {
            mapper.updateByPrimaryKey(permission);
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            mapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public Permission getById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> getAllList() {
        return mapper.selectAll();
    }

    @Override
    public PageResult<Permission> selectByQuery(QueryObject qo) {
        //判断查询条数
        int count = mapper.selectByCount();
        if (count == 0){
            return new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),0, Collections.emptyList());
        }

        List<Permission> permissions = mapper.selectByQuery(qo);
        return new PageResult<>(qo.getCurrentPage(),qo.getPageSize(),count, permissions);
    }

    @Autowired
    private ApplicationContext ctx;//拿到spring容器对象

    @Override
    public void reload() {
        //去重,判断是否该权限已经在数据库中,没有才插入
        List<String> expressions = mapper.selectAllExpressions();

        //拿到controller对象
        //getBeansWithAnnotation  通过注解获取对象//ctx.getBeansOfType()  通过父类拿到子类对象
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> controllers = beansWithAnnotation.values();//values获取map中所有的value值
        //拿到每一个controller中的所有方法
        for (Object controller : controllers) {
            //getDeclaredMethods:获取所有的方法  getMethods:只能获取public修饰的方法         没有Declared只能获取public修饰的成员
            Method[] declaredMethods = controller.getClass().getDeclaredMethods();
            //拿到每一个方法的注解
            for (Method method : declaredMethods) {
                //根据注解类型获取注解
                RequiredPermission annotation = method.getAnnotation(RequiredPermission.class);
                //拿到注解中的value
                if (annotation != null){
                    String[] value = annotation.value();
                    //将value保存到数据库中
                    if (!expressions.contains(value[1])){
                        Permission permission = new Permission();
                        permission.setName(value[0]);
                        permission.setExpression(value[1]);
                        mapper.insert(permission);
                    }
                }
            }
        }
    }

    @Override
    public List<Permission> getAllPermissionByRoleId(Long roleId) {
        return mapper.selectAllPermissionByRoleId(roleId);
    }

    @Override
    public List<String> selectAllExpressionsByEmployeeId(Long employeeId) {
        return mapper.selectAllExpressionsByEmployeeId(employeeId);
    }
}
