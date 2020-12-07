package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.mapper.DictItemMapper;
import com.sq.jk.pojo.po.DictItem;
import com.sq.jk.query.DictItemQuery;
import com.sq.jk.service.DictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements DictItemService {
    @Autowired
    private DictItemMapper mapper;

    @Override
    public void list(DictItemQuery query) {
        // 查询条件
        LambdaQueryWrapper<DictItem> wrapper = new LambdaQueryWrapper<>();

        String keyword = query.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(DictItem::getName, keyword).or()
                    .like(DictItem::getValue, keyword);
        }

        // 分页对象
        Page<DictItem> page = new Page<>(query.getPage(), query.getSize());
        // 查询
        mapper.selectPage(page, wrapper);
        // 填充query
        query.setData(page.getRecords());
        query.setCount(page.getTotal());
        query.setPages(page.getPages());
    }
}
