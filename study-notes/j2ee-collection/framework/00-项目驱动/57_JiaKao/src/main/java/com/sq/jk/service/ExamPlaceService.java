package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.ExamPlace;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.ExamPlaceVo;
import com.sq.jk.pojo.vo.list.ProvinceVo;
import com.sq.jk.pojo.vo.req.page.ExamPlacePageReqVo;

import java.util.List;

public interface ExamPlaceService extends IService<ExamPlace> {
    PageVo<ExamPlaceVo> list(ExamPlacePageReqVo query);

    List<ProvinceVo> listRegionExamPlaces();
}