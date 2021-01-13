package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.ExamPlaceCourse;
import com.sq.jk.pojo.query.ExamPlaceCourseQuery;

public interface ExamPlaceCourseService extends IService<ExamPlaceCourse> {

    void list(ExamPlaceCourseQuery query);
}