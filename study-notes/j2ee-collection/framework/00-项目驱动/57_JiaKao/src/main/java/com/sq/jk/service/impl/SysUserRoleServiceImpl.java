package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpLambdaQueryWrapper;
import com.sq.jk.mapper.SysUserRoleMapper;
import com.sq.jk.pojo.po.SysUserRole;
import com.sq.jk.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Override
    public boolean removeByUserId(Integer userId) {
        if (userId == null || userId <= 0) return false;
        MpLambdaQueryWrapper<SysUserRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, userId);
        return baseMapper.delete(wrapper) > 0;
    }
}