package com.seemygo.shop.cloud.service.impl;

import com.seemygo.shop.cloud.mapper.GoodMapper;
import com.seemygo.shop.cloud.service.IGoodService;
import com.seemygo.shop.cloud.domain.Good;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class GoodServiceImpl implements IGoodService {

    private final GoodMapper goodMapper;

    public GoodServiceImpl(GoodMapper goodMapper) {
        this.goodMapper = goodMapper;
    }

    @Override
    public List<Good> getListByIdList(Set<Long> idList) {
        return goodMapper.selectListByIdList(idList);
    }
}
