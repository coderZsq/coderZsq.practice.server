package com.seemygo.shop.cloud.web.controller;

import com.seemygo.shop.cloud.service.ISeckillGoodService;
import com.seemygo.shop.cloud.vo.SeckillGoodVo;
import com.seemygo.shop.cloud.resp.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seckill/goods")
public class SeckillGoodController {

    private final ISeckillGoodService seckillGoodService;

    public SeckillGoodController(ISeckillGoodService seckillGoodService) {
        this.seckillGoodService = seckillGoodService;
    }

    @GetMapping
    public Result<List<SeckillGoodVo>> query() {
        List<SeckillGoodVo> list = seckillGoodService.query();
        return Result.success(list);
    }

    @GetMapping("/{seckillId}")
    public Result<SeckillGoodVo> findById(@PathVariable("seckillId") Long seckillId) {
        return Result.success(seckillGoodService.detail(seckillId));
    }
}
