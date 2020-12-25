package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.enhance.MpQueryWrapper;
import com.sq.jk.mapper.PlateRegionMapper;
import com.sq.jk.pojo.po.PlateRegion;
import com.sq.jk.pojo.query.CityQuery;
import com.sq.jk.pojo.query.ProvinceQuery;
import com.sq.jk.service.PlateRegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlateRegionServiceImpl extends ServiceImpl<PlateRegionMapper, PlateRegion> implements PlateRegionService {

    @Override
    @Transactional(readOnly = true)
    public void listProvinces(ProvinceQuery query) {
        MpQueryWrapper<PlateRegion> wrapper = new MpQueryWrapper<>();
        wrapper.like(query.getKeyword(),
                PlateRegion::getName,
                PlateRegion::getPlate,
                PlateRegion::getPinyin);
        // 所有省份
        wrapper.eq(PlateRegion::getParentId, 0);
        wrapper.orderByDesc(PlateRegion::getId);
        baseMapper.selectPage(new MpPage<>(query), wrapper).updateQuery();
    }

    @Override
    @Transactional(readOnly = true)
    public void listCities(CityQuery query) {
        MpQueryWrapper<PlateRegion> wrapper = new MpQueryWrapper<>();
        wrapper.like(query.getKeyword(),
                PlateRegion::getName,
                PlateRegion::getPlate,
                PlateRegion::getPinyin);
        Integer provinceId = query.getProvinceId();
        if (provinceId != null && provinceId > 0) { // provinceId下面的所有城市
            wrapper.eq(PlateRegion::getParentId, provinceId);
        } else { // 所有城市
            wrapper.ne(PlateRegion::getParentId, 0);
        }
        wrapper.orderByDesc(PlateRegion::getId);
        baseMapper.selectPage(new MpPage<>(query), wrapper).updateQuery();
    }
}