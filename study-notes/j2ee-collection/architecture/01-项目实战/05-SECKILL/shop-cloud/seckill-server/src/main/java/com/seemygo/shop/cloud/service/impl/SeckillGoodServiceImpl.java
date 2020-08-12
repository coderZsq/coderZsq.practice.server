package com.seemygo.shop.cloud.service.impl;

import com.seemygo.shop.cloud.mapper.SeckillGoodMapper;
import com.seemygo.shop.cloud.redis.key.SeckillRedisKey;
import com.seemygo.shop.cloud.service.ISeckillGoodService;
import com.seemygo.shop.cloud.util.JSONUtil;
import com.seemygo.shop.cloud.vo.SeckillGoodVo;
import com.seemygo.shop.cloud.domain.Good;
import com.seemygo.shop.cloud.domain.SeckillGood;
import com.seemygo.shop.cloud.resp.Result;
import com.seemygo.shop.cloud.web.feign.GoodFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Slf4j
public class SeckillGoodServiceImpl implements ISeckillGoodService {

    private final SeckillGoodMapper seckillGoodMapper;
    private final GoodFeignApi goodFeignApi;
    private final StringRedisTemplate redisTemplate;

    public SeckillGoodServiceImpl(SeckillGoodMapper seckillGoodMapper, GoodFeignApi goodFeignApi, StringRedisTemplate redisTemplate) {
        this.seckillGoodMapper = seckillGoodMapper;
        this.goodFeignApi = goodFeignApi;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<SeckillGoodVo> query() {
        // 1. 查询出秒杀商品列表
        List<SeckillGood> seckillGoods = seckillGoodMapper.selectList();
        return join(seckillGoods);
    }

    @Override
    public SeckillGoodVo detail(Long seckillId) {
        // 根据id得到秒杀商品对象
        SeckillGood seckillGood = seckillGoodMapper.selectByPrimaryKey(seckillId);
        List<SeckillGoodVo> voList = join(Collections.singletonList(seckillGood));
        return CollectionUtils.isEmpty(voList) ? null : voList.get(0);
    }

    @Override
    public SeckillGoodVo detailByCache(Long seckillId) {
        String json = redisTemplate.opsForValue().get(SeckillRedisKey.SECKILL_GOODS_DETAIL.join(seckillId + ""));
        if (!StringUtils.isEmpty(json)) {
            return JSONUtil.parseObject(json, SeckillGoodVo.class);
        }
        return null;
    }

    @Override
    public int decrStockCount(Long seckillId) {
        return seckillGoodMapper.decrStockCount(seckillId);
    }

    @Override
    public SeckillGood findById(Long seckillId) {
        return seckillGoodMapper.selectByPrimaryKey(seckillId);
    }

    private List<SeckillGoodVo> join(List<SeckillGood> seckillGoods) {
        // 2. 根据秒杀商品列表（Set），得到商品id
        Set<Long> idList = new HashSet<>(seckillGoods.size());
        for (SeckillGood seckillGood : seckillGoods) {
            idList.add(seckillGood.getGoodId());
        }

//        idList = seckillGoods.stream().map(SeckillGood::getId).collect(Collectors.toSet());
        // 3. 通过商品id列表，调用远程商品服务，查询商品列表
        Result<List<Good>> goodListResult = goodFeignApi.getListByIdList(idList);
        /**
         * result可能有几种情况？
         * 正常返回：code=200
         * 异常方法调用，进入advice：code!=200
         * 进入降级方法：result=null
         */
        if (goodListResult == null || goodListResult.hasError()) {
            // TODO 查询商品数据异常，记录异常情况，并且返回null给前端用户
            // TODO 如果想提示前端用户，可以将result的状态码和msg返回
            return null;
        }

        // 正常调用
        List<Good> goodList = goodListResult.getData();
        Map<Long, Good> tmpCache = new HashMap<>(goodList.size());
        for (Good good : goodList) {
            tmpCache.put(good.getId(), good);
        }
//        goodList.forEach(g -> tmpCache.put(g.getId(), g));

        // 4. 将得到的商品列表和秒杀商品列表进行聚合，最终得到一个秒杀vo列表
        List<SeckillGoodVo> voList = new ArrayList<>(seckillGoods.size());
        for (SeckillGood seckillGood : seckillGoods) {
            SeckillGoodVo vo = new SeckillGoodVo();

            // 秒杀对象属性拷贝
            BeanUtils.copyProperties(seckillGood, vo);

            Good good = tmpCache.get(seckillGood.getGoodId());
            if (good != null) {
                BeanUtils.copyProperties(good, vo);
            }

            vo.setId(seckillGood.getId());
            voList.add(vo);
        }

        return voList;
    }
}
