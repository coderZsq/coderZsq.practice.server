package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.enhance.MpQueryWrapper;
import com.sq.jk.mapper.ProvinceMapper;
import com.sq.jk.pojo.po.Province;
import com.sq.jk.pojo.query.ProvinceQuery;
import com.sq.jk.service.ProvinceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {
    @Override
    public void list(ProvinceQuery query) {
        MpQueryWrapper<Province> wrapper = new MpQueryWrapper<>();
        wrapper.like(query.getKeyword(), Province::getName, Province::getPlate);
        // 按照id降序
        wrapper.orderByDesc(Province::getId);

        baseMapper.selectPage(new MpPage<>(query), wrapper).updateQuery(query);
    }
}