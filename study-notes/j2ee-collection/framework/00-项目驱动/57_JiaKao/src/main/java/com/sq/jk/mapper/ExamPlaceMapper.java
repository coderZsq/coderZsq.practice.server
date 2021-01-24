package com.sq.jk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sq.jk.pojo.vo.list.ProvinceVo;
import com.sq.jk.pojo.po.ExamPlace;

import java.util.List;

public interface ExamPlaceMapper extends BaseMapper<ExamPlace> {

    List<ProvinceVo> selectRegionExamPlaces();
}