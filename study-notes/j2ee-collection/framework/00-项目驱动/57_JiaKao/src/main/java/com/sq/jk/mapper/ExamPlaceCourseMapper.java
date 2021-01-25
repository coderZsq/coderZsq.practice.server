package com.sq.jk.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.pojo.po.ExamPlaceCourse;
import com.sq.jk.pojo.vo.list.ExamPlaceCourseVo;
import org.apache.ibatis.annotations.Param;

public interface ExamPlaceCourseMapper extends BaseMapper<ExamPlaceCourse> {
    MpPage<ExamPlaceCourseVo> selectPageVos(MpPage<ExamPlaceCourseVo> page,
                                            @Param(Constants.WRAPPER) Wrapper<ExamPlaceCourseVo> wrapper);
}