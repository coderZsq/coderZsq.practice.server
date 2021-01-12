package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.mapper.ExamPlaceCourceMapper;
import com.sq.jk.pojo.po.ExamPlaceCource;
import com.sq.jk.service.ExamPlaceCourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamPlaceCourceServiceImpl extends ServiceImpl<ExamPlaceCourceMapper, ExamPlaceCource> implements ExamPlaceCourceService {

}