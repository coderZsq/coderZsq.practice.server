package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.SysRoleResource;

public interface SysRoleResourceService extends IService<SysRoleResource> {

    boolean removeByRoleId(Short roleId);
}