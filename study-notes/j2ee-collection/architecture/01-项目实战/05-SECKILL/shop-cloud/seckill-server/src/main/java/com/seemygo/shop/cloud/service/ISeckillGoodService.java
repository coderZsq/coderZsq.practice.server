package com.seemygo.shop.cloud.service;

import com.seemygo.shop.cloud.vo.SeckillGoodVo;
import com.seemygo.shop.cloud.domain.SeckillGood;

import java.util.List;

/**
 * Created by wolfcode-lanxw
 */
public interface ISeckillGoodService {

    /**
     * 查询秒杀商品列表
     *
     * @return
     */
    List<SeckillGoodVo> query();


    /**
     * 根据秒杀id查询秒杀详情信息
     *
     * @param seckillId
     * @return
     */
    SeckillGoodVo detail(Long seckillId);

    int decrStockCount(Long seckillId);

    /**
     * 从数据库查询最新的秒杀商品对象
     * @param seckillId
     * @return
     */
    SeckillGood findById(Long seckillId);
}
