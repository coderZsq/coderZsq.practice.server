package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.util.Rs;
import com.sq.jk.pojo.po.ExamPlace;
import com.sq.jk.pojo.query.ExamPlaceQuery;
import com.sq.jk.pojo.result.R;
import com.sq.jk.service.ExamPlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/examPlaces")
@Api(tags = "考场")
public class ExamPlaceController extends BaseController<ExamPlace> {
    @Autowired
    private ExamPlaceService service;

    @GetMapping
    public R list(ExamPlaceQuery query) {
        service.list(query);
        return Rs.ok(query);
    }

    @GetMapping("/regionExamPlaces")
    @ApiOperation("查询所有区域下面的考场信息")
    public R listRegionExamPlaces() {
        return Rs.ok(service.listRegionExamPlaces());
    }

    @Override
    protected IService<ExamPlace> getService() {
        return service;
    }
}