package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.enhance.MpQueryWrapper;
import com.sq.jk.common.util.Strings;
import com.sq.jk.mapper.ExamPlaceCourseMapper;
import com.sq.jk.pojo.po.ExamPlaceCourse;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.ExamPlaceCourseVo;
import com.sq.jk.pojo.vo.req.page.ExamPlaceCoursePageReqVo;
import com.sq.jk.service.ExamPlaceCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamPlaceCourseServiceImpl extends ServiceImpl<ExamPlaceCourseMapper, ExamPlaceCourse> implements ExamPlaceCourseService {

    @Override
    public PageVo<ExamPlaceCourseVo> list(ExamPlaceCoursePageReqVo query) {
        MpQueryWrapper<ExamPlaceCourseVo> wrapper = new MpQueryWrapper<>();
        Integer placeId = query.getPlaceId();
        Integer provinceId = query.getProvinceId();
        Integer cityId = query.getCityId();
        Short type = query.getType();
        // 类型
        if (type != null && type >= 0) {
            wrapper.eq("type", type);
        }
        // 考场 -> 城市 -> 省份
        if (placeId != null && placeId > 0) {
            wrapper.eq("place_id", placeId);
        } else if (cityId != null && cityId > 0) {
            wrapper.eq("city_id", cityId);
        } else if (provinceId != null && provinceId > 0) {
            wrapper.eq("province_id", provinceId);
        }
        // 关键词
        wrapper.like(query.getKeyword(), "c.name", "c.intro");
        return baseMapper
                .selectPageVos(new MpPage<>(query), wrapper)
                .buildVo();
        // MpQueryWrapper<ExamPlaceCourseVo> wrapper = new MpQueryWrapper<>();
        // Integer placeId = query.getPlaceId();
        // Integer provinceId = query.getProvinceId();
        // Integer cityId = query.getCityId();
        // Short type = query.getType();
        // // 类型
        // if (type != null && type >= 0) {
        //     wrapper.eq(ExamPlaceCourseVo::getType, type);
        // }
        // // 考场 -> 城市 -> 省份
        // if (placeId != null && placeId > 0) {
        //     wrapper.eq(ExamPlaceCourseVo::getPlaceId, placeId);
        // } else if (cityId != null && cityId > 0) {
        //     wrapper.eq(ExamPlaceCourseVo::getCityId, cityId);
        // } else if (provinceId != null && provinceId > 0) {
        //     wrapper.eq(ExamPlaceCourseVo::getProvinceId, provinceId);
        // }
        // // 关键词
        // wrapper.like(query.getKeyword(), ExamPlaceCourseVo::getName, ExamPlaceCourseVo::getIntro);
        // return baseMapper
        //         .selectPageVos(new MpPage<>(query), wrapper)
        //         .buildVo();
        // 通过province_id查询时: Unknown column 'province_id' in 'where clause'
        // 通过city_id查询时: Unknown column 'city_id' in 'where clause'
        // 通过name查询时: Column 'name' in where clause is ambiguous
    }
}