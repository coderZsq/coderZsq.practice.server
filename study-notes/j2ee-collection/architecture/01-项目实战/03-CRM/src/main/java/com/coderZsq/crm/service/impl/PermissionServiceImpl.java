package com.coderZsq.crm.service.impl;

import com.coderZsq.crm.domain.Permission;
import com.coderZsq.crm.mapper.PermissionMapper;
import com.coderZsq.crm.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import com.coderZsq.crm.query.QueryObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * (Permission)表服务实现类
 *
 * @author makejava
 * @since 2020-03-20 14:10:40
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    // 注入spring容器对象
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Permission queryById(Long id) {
        return this.permissionMapper.queryById(id);
    }

    /**
     * 查询多条数据
     * @return 对象列表
     */
    @Override
    public List<Permission> queryAll() {
        return this.permissionMapper.queryAll();
    }
	
    /**
     * 根据条件查询数据
     * @return 对象列表
     */
	@Override
    public PageInfo<Permission> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Permission> list = permissionMapper.query(qo);
        return new PageInfo<>(list);
    }

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission insert(Permission permission) {
        this.permissionMapper.insert(permission);
        return permission;
    }

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    @Override
    public Permission update(Permission permission) {
        this.permissionMapper.update(permission);
        return this.queryById(permission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.permissionMapper.deleteById(id) > 0;
    }

    @Override
    public void reload() {
        // 1 需要找到所有的Controller ---> Spring容器中获取ApplicationContext
        // <"roleController", RoleController@123fid>
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(Controller.class);
        Collection<Object> beans = beanMap.values(); // 所有的controller实例
        List<String> expressions = permissionMapper.queryExp(); // 查询所有的权限表达式
        Permission permission = new Permission();
        // 2 找到每个controller实例的方法
        for (Object bean : beans) {
            // 获取当前类的所有方法, 不会包括父类的
            Method[] ms = bean.getClass().getDeclaredMethods();
            // 判断方法是否有权限标记
            for (Method m : ms) {
                RequiresPermissions rp = m.getAnnotation(RequiresPermissions.class);
                // 3 判断当前方法是否需要权限校验 判断是否贴了权限校验的标签
                if (rp != null) {
                    String[] value = rp.value();
                    String name = value[0];
                    String exp = value[1];
                    // 4 如果需要权限校验
                    // 4.1 判断数据库中是否包含权限数据, 如果已经包含, 跳过, 如果不包含, 新增权限项到数据库
                    if (!expressions.contains(exp)) { // 数据库不存在
                        permission.setName(name);
                        permission.setExpression(exp);
                        permissionMapper.insert(permission);
                    }
                }
            }
        }
    }
}