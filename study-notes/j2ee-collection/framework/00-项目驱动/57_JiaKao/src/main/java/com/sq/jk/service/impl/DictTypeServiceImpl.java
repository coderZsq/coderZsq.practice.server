package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.mapper.DictTypeMapper;
import com.sq.jk.pojo.po.DictType;
import com.sq.jk.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService {
    @Autowired
    private DictTypeMapper mapper;

    @Override
    public IPage<DictType> list(long page, long limit, String keyword) {
        Page<DictType> mpPage = new Page<>(page, limit);
        LambdaQueryWrapper<DictType> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(DictType::getName, keyword).or()
                    .like(DictType::getValue, keyword).or()
                    .like(DictType::getIntro, keyword);
        }
        wrapper.orderByDesc(DictType::getId);
        return mapper.selectPage(mpPage, wrapper);
    }
}