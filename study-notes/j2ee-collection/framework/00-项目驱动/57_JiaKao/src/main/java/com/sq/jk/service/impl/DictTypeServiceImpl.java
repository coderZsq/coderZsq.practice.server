package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.enhance.MpPage;
import com.sq.jk.enhance.MpQueryWrapper;
import com.sq.jk.mapper.DictTypeMapper;
import com.sq.jk.pojo.po.DictType;
import com.sq.jk.pojo.query.DictTypeQuery;
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
        MpQueryWrapper<DictType> wrapper = new MpQueryWrapper<>();
        if (!StringUtils.isEmpty(query.getKeyword())) {
            String keyword = query.getKeyword();
            wrapper.like(DictType::getName, keyword).or()
                    .like(DictType::getValue, keyword).or()
                    .like(DictType::getIntro, keyword);
        }
        wrapper.orderByDesc(DictType::getId); // 按照id降序

        // 分页查询
        mapper.selectPage(new MpPage<>(query), wrapper).updateQuery(query);
    }
}