package com.sq.jk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sq.jk.pojo.po.ExamPlace;
import com.sq.jk.pojo.query.ExamPlaceQuery;

public interface ExamPlaceService extends IService<ExamPlace> {
    void list(ExamPlaceQuery query);
}