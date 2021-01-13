package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.enhance.MpQueryWrapper;
import com.sq.jk.mapper.ExamPlaceMapper;
import com.sq.jk.pojo.dto.ProvinceDto;
import com.sq.jk.pojo.po.DictItem;
import com.sq.jk.pojo.po.ExamPlace;
import com.sq.jk.pojo.query.ExamPlaceQuery;
import com.sq.jk.service.ExamPlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamPlaceServiceImpl extends ServiceImpl<ExamPlaceMapper, ExamPlace> implements ExamPlaceService {

    @Override
    @Transactional(readOnly = true)
    public void list(ExamPlaceQuery query) {
        // 查询条件
        MpQueryWrapper<ExamPlace> wrapper = new MpQueryWrapper<>();
        wrapper.like(query.getKeyword(), ExamPlace::getName, ExamPlace::getAddress);

        // 城市
        Integer cityId = query.getCityId();
        Integer provinceId = query.getProvinceId();
        if (cityId != null && cityId > 0) {
            wrapper.eq(ExamPlace::getCityId, cityId);
        } else if (provinceId != null && provinceId > 0) {
            wrapper.eq(ExamPlace::getProvinceId, provinceId);
        }

        // 排序
        wrapper.orderByDesc(ExamPlace::getId);

        // 查询
        baseMapper.selectPage(new MpPage<>(query), wrapper).updateQuery();
    }

    @Override
    public List<ProvinceDto> listRegionExamPlaces() {
        return baseMapper.selectRegionExamPlaces();
    }
}