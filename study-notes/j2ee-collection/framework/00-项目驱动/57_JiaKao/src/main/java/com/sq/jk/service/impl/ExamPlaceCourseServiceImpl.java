package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.enhance.MpQueryWrapper;
import com.sq.jk.mapper.ExamPlaceCourseMapper;
import com.sq.jk.pojo.po.ExamPlaceCourse;
import com.sq.jk.pojo.query.ExamPlaceCourseQuery;
import com.sq.jk.service.ExamPlaceCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamPlaceCourseServiceImpl extends ServiceImpl<ExamPlaceCourseMapper, ExamPlaceCourse> implements ExamPlaceCourseService {

    @Override
    public void list(ExamPlaceCourseQuery query) {
        MpQueryWrapper<ExamPlaceCourse> wrapper = new MpQueryWrapper<>();

        baseMapper.selectPage(new MpPage<>(query), wrapper).updateQuery();
    }
}