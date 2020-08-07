package com.seemygo.shop.cloud.web.controller;

import com.seemygo.shop.cloud.domain.User;
import com.seemygo.shop.cloud.util.JSONUtil;
import com.seemygo.shop.cloud.redis.MemberRedisKey;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class BaseController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    User getCurrentUser(String token) {
        String json = redisTemplate.opsForValue().get(MemberRedisKey.USER_TOKEN_KEY.join(token));
        if (!StringUtils.isEmpty(json)) {
            return JSONUtil.parseObject(json, User.class);
        }
        return null;
    }
}
