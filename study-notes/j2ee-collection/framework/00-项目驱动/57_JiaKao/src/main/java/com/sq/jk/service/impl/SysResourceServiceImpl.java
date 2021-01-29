package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpLambdaQueryWrapper;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.Constants;
import com.sq.jk.common.util.Streams;
import com.sq.jk.mapper.SysResourceMapper;
import com.sq.jk.mapper.SysRoleResourceMapper;
import com.sq.jk.pojo.po.SysResource;
import com.sq.jk.pojo.po.SysRoleResource;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.SysResourceTreeVo;
import com.sq.jk.pojo.vo.list.SysResourceVo;
import com.sq.jk.pojo.vo.req.page.SysResourcePageReqVo;
import com.sq.jk.pojo.vo.req.save.SysResourceReqVo;
import com.sq.jk.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
    @Autowired
    private SysRoleResourceMapper roleResourceMapper;

    @Override
    @Transactional(readOnly = true)
    public PageVo<SysResourceVo> list(SysResourcePageReqVo reqVo) {
        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.like(reqVo.getKeyword(),
                SysResource::getName,
                SysResource::getUri,
                SysResource::getPermission);
        wrapper.orderByDesc(SysResource::getId);
        return baseMapper
                .selectPage(new MpPage<>(reqVo), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysResourceVo> listParents() {
        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.ne(SysResource::getType, Constants.SysResourceType.BTN);
        wrapper.orderByAsc(SysResource::getType).orderByDesc(SysResource::getId);
        return Streams.map(baseMapper.selectList(wrapper), MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysResourceTreeVo> listTree() {
        // 里面存放的是树状结构的VO (最外层是目录类型的资源对象)
        List<SysResourceTreeVo> vos = new ArrayList<>();

        // 存放已经从po转换成功的vo
        Map<Short, SysResourceTreeVo> doneVos = new HashMap<>();

        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.orderByAsc(SysResource::getType);
        // 里面存放的是从数据库中查出来的PO
        List<SysResource> pos = baseMapper.selectList(wrapper);
        for (SysResource po : pos) {
            // po转vo
            SysResourceTreeVo vo = po2treeVo(po);

            // 将vo存放到doneVos中
            doneVos.put(vo.getId(), vo);

            Short type = po.getType();
            if (type == Constants.SysResourceType.DIR) { // 目录
                vos.add(vo);
            } else { // 菜单, 按钮
                // 从doneVos中取出父VO
                SysResourceTreeVo parentVo = doneVos.get(po.getParentId());
                List<SysResourceTreeVo> children = parentVo.getChildren();
                if (children == null) {
                    parentVo.setChildren(children = new ArrayList<>());
                }
                children.add(vo);
            }
        }

        // po = [目录, 目录, ..., 菜单, ...., 按钮]
        //
        // vos = [dir_vo1, dir_vo2, dir_vo3, .....]
        //
        // map = {
        //     1: dir_vo1,
        //     2: dir_vo2,
        //     3: dir_vo3,
        //     11: menu_vo1,
        //     12: menu_vo2
        // }

        return vos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Short> listIds(Integer roleId) {
        if (roleId == null || roleId <= 0) return null;

        MpLambdaQueryWrapper<SysRoleResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.select(SysRoleResource::getResourceId);
        wrapper.eq(SysRoleResource::getRoleId, roleId);

        List<Object> ids = roleResourceMapper.selectObjs(wrapper);
        return Streams.map(ids, (id) -> ((Integer) id).shortValue());
    }

    private List<Short> listIds(List<Short> roleIds) {
        MpLambdaQueryWrapper<SysRoleResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.select(SysRoleResource::getResourceId);
        wrapper.in(SysRoleResource::getRoleId, roleIds);

        List<Object> ids = roleResourceMapper.selectObjs(wrapper);
        return Streams.map(new HashSet<>(ids), (id) -> ((Integer) id).shortValue());
    }

    @Override
    public List<SysResourceVo> listByRoleIds(List<Short> roleIds) {
        MpLambdaQueryWrapper<SysResource> wrapper = new MpLambdaQueryWrapper<>();
        wrapper.in(SysResource::getId, listIds(roleIds));
        return Streams.map(baseMapper.selectList(wrapper), MapStructs.INSTANCE::po2vo);
    }

    private SysResourceTreeVo po2treeVo(SysResource po) {
        SysResourceTreeVo treeVo = new SysResourceTreeVo();
        treeVo.setId(po.getId());
        treeVo.setTitle(po.getName());
        return treeVo;
    }
}