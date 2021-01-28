package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpLambdaQueryWrapper;
import com.sq.jk.mapper.SysRoleResourceMapper;
import com.sq.jk.pojo.po.SysRoleResource;
import com.sq.jk.service.SysRoleResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements SysRoleResourceService {

    @Override
    public boolean removeByRoleId(Short roleId) {
        if (roleId == null || roleId <= 0) return false;
        MpLambdaQueryWrapper<SysRoleResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.eq(SysRoleResource::getRoleId, roleId);
        return baseMapper.delete(wrapper) > 0;
    }
}