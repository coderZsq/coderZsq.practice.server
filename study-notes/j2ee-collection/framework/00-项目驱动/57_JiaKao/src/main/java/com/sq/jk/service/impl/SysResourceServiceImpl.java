package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpLambdaQueryWrapper;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.mapper.SysResourceMapper;
import com.sq.jk.pojo.po.SysResource;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.SysResourceVo;
import com.sq.jk.pojo.vo.req.page.SysResourcePageReqVo;
import com.sq.jk.service.SysResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

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
}