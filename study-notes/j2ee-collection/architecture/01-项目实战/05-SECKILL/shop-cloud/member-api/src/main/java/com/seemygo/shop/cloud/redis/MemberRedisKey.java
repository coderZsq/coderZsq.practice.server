package com.seemygo.shop.cloud.redis;

import lombok.Getter;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.TimeUnit;

@Getter
public enum MemberRedisKey {
    USER_TOKEN_KEY("userToken:", 30, TimeUnit.MINUTES);

    MemberRedisKey(String prefix, long expireTime, TimeUnit unit) {
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
