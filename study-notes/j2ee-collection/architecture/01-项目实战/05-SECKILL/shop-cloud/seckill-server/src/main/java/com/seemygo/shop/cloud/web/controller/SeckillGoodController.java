package com.seemygo.shop.cloud.web.controller;

import com.seemygo.shop.cloud.redis.key.SeckillRedisKey;
import com.seemygo.shop.cloud.service.ISeckillGoodService;
import com.seemygo.shop.cloud.util.JSONUtil;
import com.seemygo.shop.cloud.vo.SeckillGoodVo;
import com.seemygo.shop.cloud.resp.Result;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seckill/goods")
public class SeckillGoodController {

    private final ISeckillGoodService seckillGoodService;
    private final StringRedisTemplate redisTemplate;

    public SeckillGoodController(ISeckillGoodService seckillGoodService, StringRedisTemplate redisTemplate) {
        this.seckillGoodService = seckillGoodService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 优化前:
     *      100 * 50
     *      1052.9/s
     * @return
     */
    @GetMapping
    public Result<List<SeckillGoodVo>> query() {
        List<SeckillGoodVo> list = seckillGoodService.query();
        return Result.success(list);
    }

    @GetMapping("/{seckillId}")
    public Result<SeckillGoodVo> findById(@PathVariable("seckillId") Long seckillId) {
        return Result.success(seckillGoodService.detail(seckillId));
    }

    @GetMapping("/initData")
    public Result<String> init() {
        // 数据预热
        List<SeckillGoodVo> query = seckillGoodService.query();
        // Redis 结构? string
        // key: {} --> 过期时间
        for (SeckillGoodVo vo : query) {
            redisTemplate.opsForValue().set(
                    SeckillRedisKey.SECKILL_GOODS_DETAIL.join(vo.getId() + ""),
                    JSONUtil.toJSONString(vo),
                    SeckillRedisKey.SECKILL_GOODS_DETAIL.getExpireTime(),
                    SeckillRedisKey.SECKILL_GOODS_DETAIL.getUnit()
            );
        }
        return Result.success("success");
    }
}
