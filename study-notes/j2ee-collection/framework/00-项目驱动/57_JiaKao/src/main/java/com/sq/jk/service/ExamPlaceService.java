package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.dto.ProvinceDto;
import com.sq.jk.pojo.po.ExamPlace;
import com.sq.jk.pojo.query.ExamPlaceQuery;
import com.sq.jk.pojo.result.CodeMsg;

import java.util.List;

public interface ExamPlaceService extends IService<ExamPlace> {
    void list(ExamPlaceQuery query);

    List<ProvinceDto> listRegionExamPlaces();
}