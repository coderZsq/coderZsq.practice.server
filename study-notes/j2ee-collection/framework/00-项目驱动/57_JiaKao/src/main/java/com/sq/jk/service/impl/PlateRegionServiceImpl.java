package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.promeg.pinyinhelper.Pinyin;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.enhance.MpQueryWrapper;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.Streams;
import com.sq.jk.mapper.PlateRegionMapper;
import com.sq.jk.pojo.po.PlateRegion;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.PlateRegionVo;
import com.sq.jk.pojo.vo.list.ProvinceVo;
import com.sq.jk.pojo.vo.req.page.CityPageReqVo;
import com.sq.jk.pojo.vo.req.page.ProvincePageReqVo;
import com.sq.jk.service.PlateRegionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlateRegionServiceImpl extends ServiceImpl<PlateRegionMapper, PlateRegion> implements PlateRegionService {

    @Override
    public boolean save(PlateRegion entity) {
        processPinyin(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(PlateRegion entity) {
        processPinyin(entity);
        return super.updateById(entity);
    }

    private void processPinyin(PlateRegion region) {
        String name = region.getName();
        if (name == null) return;

        region.setPinyin(Pinyin.toPinyin(name, "_"));
    }

    @Override
    public List<ProvinceVo> listRegions() {
        return baseMapper.selectRegions();
    }

    // @Override
    // public List<ProvinceDto> listRegions() {
    //     List<PlateRegion> dbRegions = baseMapper.selectList(null);
    //     List<ProvinceDto> regions = new ArrayList<>();
    //     for (PlateRegion dbRegion : dbRegions) {
    //
    //     }
    //     return regions;
    // }

    @Override
    @Transactional(readOnly = true)
    public PageVo<PlateRegionVo> listProvinces(ProvincePageReqVo query) {
        MpQueryWrapper<PlateRegion> wrapper = new MpQueryWrapper<>();
        wrapper.like(query.getKeyword(),
                PlateRegion::getName,
                PlateRegion::getPlate,
                PlateRegion::getPinyin);
        // 所有省份
        wrapper.eq(PlateRegion::getParentId, 0);
        wrapper.orderByDesc(PlateRegion::getId);
        return baseMapper
                .selectPage(new MpPage<>(query), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public PageVo<PlateRegionVo> listCities(CityPageReqVo query) {
        MpQueryWrapper<PlateRegion> wrapper = new MpQueryWrapper<>();
        wrapper.like(query.getKeyword(),
                PlateRegion::getName,
                PlateRegion::getPlate,
                PlateRegion::getPinyin);
        Integer provinceId = query.getParentId();
        if (provinceId != null && provinceId > 0) { // provinceId下面的所有城市
            wrapper.eq(PlateRegion::getParentId, provinceId);
        } else { // 所有城市
            wrapper.ne(PlateRegion::getParentId, 0);
        }
        wrapper.orderByDesc(PlateRegion::getId);
        return baseMapper
                .selectPage(new MpPage<>(query), wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlateRegionVo> listProvinces() {
        MpQueryWrapper<PlateRegion> wrapper = new MpQueryWrapper<>();
        wrapper.eq(PlateRegion::getParentId, 0);
        wrapper.orderByAsc(PlateRegion::getPinyin);
        return Streams.map(baseMapper.selectList(wrapper), MapStructs.INSTANCE::po2vo);
    }
}