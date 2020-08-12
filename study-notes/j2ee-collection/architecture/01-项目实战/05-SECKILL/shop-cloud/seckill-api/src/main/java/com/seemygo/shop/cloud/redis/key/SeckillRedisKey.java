package com.seemygo.shop.cloud.redis.key;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.TimeUnit;

@Getter
public enum SeckillRedisKey {
    /**
     * 秒杀详情数据: STRING
     */
    SECKILL_GOODS_DETAIL("seckillGoodsDetail:", 2, TimeUnit.DAYS),
    /**
     * 用户秒杀记录
     */
    SECKILL_USER_RECORD("seckillUserRecord:");

    SeckillRedisKey(String prefix) {
        this(prefix, 0, null);
    }

    SeckillRedisKey(String prefix, long expireTime, TimeUnit unit) {
        this.prefix = prefix;
        this.expireTime = expireTime;
        this.unit = unit;
    }

    // 前缀
    private String prefix;
    // 过期时间
    private Long expireTime;
    // 过期时间对应的单位
    private TimeUnit unit;

    public String join(String ...key) {
        return this.getPrefix() + StringUtils.join(key, ":");
    }
}
