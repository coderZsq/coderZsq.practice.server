package com.seemygo.shop.cloud.web.feign;

import com.seemygo.shop.cloud.domain.Good;
import com.seemygo.shop.cloud.resp.Result;
import com.seemygo.shop.cloud.web.feign.hystrix.GoodFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "goods-server", fallback = GoodFeignHystrix.class)
public interface GoodFeignApi {

    @RequestMapping("/getListByIdList")
    Result<List<Good>> getListByIdList(@RequestParam("idList") Set<Long> idList);
}
