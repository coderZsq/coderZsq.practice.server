package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.util.Rs;
import com.sq.jk.pojo.po.ExamPlaceCourse;
import com.sq.jk.pojo.query.ExamPlaceCourseQuery;
import com.sq.jk.pojo.result.R;
import com.sq.jk.service.ExamPlaceCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examPlaceCourses")
public class ExamPlaceCourseController extends BaseController<ExamPlaceCourse> {
    @Autowired
    private ExamPlaceCourseService service;

    @Override
    protected IService<ExamPlaceCourse> getService() {
        return service;
    }

    @GetMapping
    public R list(ExamPlaceCourseQuery query) {
        service.list(query);
        return Rs.ok(query);
    }
}