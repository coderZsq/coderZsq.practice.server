package com.sq.jk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sq.jk.common.enhance.MpPage;
import com.sq.jk.common.enhance.MpQueryWrapper;
import com.sq.jk.mapper.DictTypeMapper;
import com.sq.jk.pojo.po.DictType;
import com.sq.jk.pojo.query.DictTypeQuery;
import com.sq.jk.service.DictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService {
    @Override
    public void list(DictTypeQuery query) {
        // 查询条件
        MpQueryWrapper<DictType> wrapper = new MpQueryWrapper<>();

        // 根据关键字查询
        wrapper.like(query.getKeyword(),
                DictType::getName,
                DictType::getValue,
                DictType::getIntro);

        // 按照id降序
        wrapper.orderByDesc(DictType::getId);

        // 分页查询
        baseMapper.selectPage(new MpPage<>(query), wrapper).updateQuery();
    }
}