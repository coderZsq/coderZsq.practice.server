package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpLambdaQueryWrapper;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.Streams;
import com.sq.jk.common.util.Strings;
import com.sq.jk.mapper.SysRoleMapper;
import com.sq.jk.mapper.SysUserRoleMapper;
import com.sq.jk.pojo.po.SysRole;
import com.sq.jk.pojo.po.SysRoleResource;
import com.sq.jk.pojo.po.SysUser;
import com.sq.jk.pojo.po.SysUserRole;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.SysRoleVo;
import com.sq.jk.pojo.vo.req.page.SysRolePageReqVo;
import com.sq.jk.pojo.vo.req.save.SysRoleReqVo;
import com.sq.jk.service.SysRoleResourceService;
import com.sq.jk.service.SysRoleService;
import com.sq.jk.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysRoleResourceService roleResourceService;

    @Override
    @Transactional(readOnly = true)
    public PageVo<SysRoleVo> list(SysRolePageReqVo reqVo) {
        MpLambdaQueryWrapper<SysRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(), SysRole::getName);
        wrapper.orderByDesc(SysRole::getId);
        return baseMapper
                .selectPage(new MpPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Short> listIds(Integer userId) {
        if (userId == null || userId <= 0) return null;

        MpLambdaQueryWrapper<SysUserRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.select(SysUserRole::getRoleId);
        wrapper.eq(SysUserRole::getUserId, userId);

        List<Object> ids = userRoleMapper.selectObjs(wrapper);
        return Streams.map(ids, (id) -> ((Integer) id).shortValue());
    }

    @Override
    public boolean saveOrUpdate(SysRoleReqVo reqVo) {
        // 转成PO
        SysRole po = MapStructs.INSTANCE.reqVo2po(reqVo);

        // 保存角色信息
        if (!saveOrUpdate(po)) return false;

        // 删除当前角色的所有资源信息
        roleResourceService.removeByRoleId(reqVo.getId());

        // 保存资源信息
        String resourceIdsStr = reqVo.getResourceIds();
        if (Strings.isEmpty(resourceIdsStr)) return true;

        String[] resourceIds = resourceIdsStr.split(",");
        List<SysRoleResource> roleResources = new ArrayList<>();
        Short roleId = po.getId();
        for (String resourceId : resourceIds) { // 构建SysRoleResource对象
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(Short.parseShort(resourceId));
            roleResources.add(roleResource);
        }
        return roleResourceService.saveBatch(roleResources);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysRoleVo> listByUserId(Integer userId) {
        MpLambdaQueryWrapper<SysRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.in(SysRole::getId, listIds(userId));
        // String sql = "SELECT role_id FROM sys_user_role WHERE user_id = " + userId;
        // wrapper.inSql(SysRole::getId, sql);
        return Streams.map(baseMapper.selectList(wrapper), MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysRole> list() {
        MpLambdaQueryWrapper<SysRole> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.orderByDesc(SysRole::getId);
        return super.list(wrapper);
    }
}