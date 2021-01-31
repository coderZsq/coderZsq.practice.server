package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.ExamPlaceCourse;
import com.sq.jk.pojo.vo.PageVo;
import com.sq.jk.pojo.vo.list.ExamPlaceCourseVo;
import com.sq.jk.pojo.vo.req.page.ExamPlaceCoursePageReqVo;
import com.sq.jk.pojo.vo.req.save.ExamPlaceCourseReqVo;

public interface ExamPlaceCourseService extends IService<ExamPlaceCourse> {
    PageVo<ExamPlaceCourseVo> list(ExamPlaceCoursePageReqVo query);

    boolean saveOrUpdate(ExamPlaceCourseReqVo examPlaceCourseReqVo);
}