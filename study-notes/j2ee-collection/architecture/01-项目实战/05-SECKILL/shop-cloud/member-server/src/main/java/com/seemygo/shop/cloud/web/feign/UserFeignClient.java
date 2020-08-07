package com.seemygo.shop.cloud.web.feign;

import com.seemygo.shop.cloud.resp.Result;
import com.seemygo.shop.cloud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFeignClient implements UserFeignApi {

    @Autowired
    private IUserService userService;

    @Override
    public Result<Boolean> refreshToken(String token) {
        boolean refreshed = userService.refreshToken(token);
        return Result.success(refreshed);
    }
}
