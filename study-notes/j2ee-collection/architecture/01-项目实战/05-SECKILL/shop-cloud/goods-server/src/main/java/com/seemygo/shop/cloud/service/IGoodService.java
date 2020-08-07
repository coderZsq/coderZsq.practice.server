package com.seemygo.shop.cloud.service;

import com.seemygo.shop.cloud.domain.Good;

import java.util.List;
import java.util.Set;

public interface IGoodService {

    /**
     * 根据id列表查询商品列表
     *
     * @param idList
     * @return
     */
    List<Good> getListByIdList(Set<Long> idList);
}
