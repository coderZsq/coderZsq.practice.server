package com.sq.jk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.jk.pojo.dto.ProvinceDto;
import com.sq.jk.pojo.po.ExamPlace;

import java.util.List;

public interface ExamPlaceMapper extends BaseMapper<ExamPlace> {

    List<ProvinceDto> selectRegionExamPlaces();
}