package cn.wolfcode.wolf2w.redis.service.impl;

import cn.wolfcode.wolf2w.redis.service.ISecurityRedisService;
import cn.wolfcode.wolf2w.redis.util.RedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SecurityRedisServiceImpl implements ISecurityRedisService {

    @Autowired
    private StringRedisTemplate template;


    @Override
    public boolean isAllowBrush(String key) {
        //如果有不做 任何操作，如果没有添加
        //setnx key value : 如果key存在， 不操作， 如果不存在， 添加
        template.opsForValue().setIfAbsent(key, "10", RedisKeys.BRUSH_PROOF.getTime(), TimeUnit.SECONDS);
        Long decrement = template.opsForValue().decrement(key);
        return decrement >= 0;
    }
}