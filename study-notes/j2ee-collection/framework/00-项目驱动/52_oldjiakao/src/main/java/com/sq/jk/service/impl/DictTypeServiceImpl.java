package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.mapper.DictTypeMapper;
import com.sq.jk.pojo.po.DictType;
import com.sq.jk.query.DictTypeQuery;
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
    public void list(DictTypeQuery query) {
        // 查询条件
        LambdaQueryWrapper<DictType> wrapper = new LambdaQueryWrapper<>();

        String keyword = query.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(DictType::getName, keyword).or()
                    .like(DictType::getValue, keyword).or()
                    .like(DictType::getIntro, keyword);
        }

        // 分页对象
        Page<DictType> page = new Page<>(query.getPage(), query.getSize());
        // 查询
        mapper.selectPage(page, wrapper);
        // 填充query
        query.setData(page.getRecords());
        query.setCount(page.getTotal());
        query.setPages(page.getPages());
    }
}
