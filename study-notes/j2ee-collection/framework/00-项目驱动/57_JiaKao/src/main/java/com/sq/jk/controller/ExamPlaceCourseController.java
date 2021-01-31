package com.sq.jk.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.common.mapStruct.MapStructs;
import com.sq.jk.common.util.Constants;
import com.sq.jk.common.util.JsonVos;
import com.sq.jk.pojo.po.ExamPlaceCourse;
import com.sq.jk.pojo.result.CodeMsg;
import com.sq.jk.pojo.vo.JsonVo;
import com.sq.jk.pojo.vo.PageJsonVo;
import com.sq.jk.pojo.vo.list.ExamPlaceCourseVo;
import com.sq.jk.pojo.vo.req.page.ExamPlaceCoursePageReqVo;
import com.sq.jk.pojo.vo.req.save.ExamPlaceCourseReqVo;
import com.sq.jk.service.ExamPlaceCourseService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
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
        if (service.saveOrUpdate(examPlaceCourseReqVo)) {
            return JsonVos.ok(CodeMsg.SAVE_OK);
        } else {
            return JsonVos.raise(CodeMsg.SAVE_ERROR);
        }
    }

    @Override
    @RequiresPermissions(Constants.Permission.EXAM_PLACE_COURSE_REMOVE)
    public JsonVo remove(String id) {
        List<String> idStrs = Arrays.asList(id.split(","));
        if (CollectionUtils.isEmpty(idStrs)) {
            return JsonVos.raise(CodeMsg.REMOVE_ERROR);
        }

        boolean ret = true;
        for (String idStr : idStrs) {
            if (!service.removeById(idStr)) {
                ret = false;
            }
        }

        return ret ? JsonVos.ok(CodeMsg.REMOVE_OK) : JsonVos.raise(CodeMsg.REMOVE_ERROR);
    }
}