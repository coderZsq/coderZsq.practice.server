package com.sq.jk.service.impl;

import com.sq.jk.mapper.DictTypeMapper;
import com.sq.jk.pojo.po.DictType;
import com.sq.jk.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    private DictTypeMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<DictType> list() {
        return mapper.list();
    }
}
