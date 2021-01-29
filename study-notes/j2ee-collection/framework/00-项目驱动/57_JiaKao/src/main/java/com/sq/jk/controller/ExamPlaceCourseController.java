package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.Constants;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.pojo.po.ExamPlaceCourse;
import com.sq.jk.pojo.vo.JsonVo;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.ExamPlaceCourseVo;
import com.sq.jk.pojo.vo.req.page.ExamPlaceCoursePageReqVo;
import com.sq.jk.pojo.vo.req.save.ExamPlaceCourseReqVo;
import com.sq.jk.service.ExamPlaceCourseService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.function.Function;

@RestController
@RequestMapping("/examPlaceCourses")
public class ExamPlaceCourseController extends BaseController<ExamPlaceCourse, ExamPlaceCourseReqVo> {
    @Autowired
    private ExamPlaceCourseService service;

    @Override
    protected IService<ExamPlaceCourse> getService() {
        return service;
    }

    @Override
    protected Function<ExamPlaceCourseReqVo, ExamPlaceCourse> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(Constants.Permission.EXAM_PLACE_COURSE_LIST)
    public PageJsonVo<ExamPlaceCourseVo> list(ExamPlaceCoursePageReqVo query) {
        return JsonVos.ok(service.list(query));
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permission.EXAM_PLACE_COURSE_ADD,
            Constants.Permission.EXAM_PLACE_COURSE_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(ExamPlaceCourseReqVo examPlaceCourseReqVo) {
        return super.save(examPlaceCourseReqVo);
    }

    @Override
    @RequiresPermissions(Constants.Permission.EXAM_PLACE_COURSE_REMOVE)
    public JsonVo remove(String id) {
        return super.remove(id);
    }
}