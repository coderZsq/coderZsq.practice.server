package com.sq.jk.common.cache;

import com.sq.jk.pojo.dto.SysUserDto;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class TokenCacheListener implements CacheEventListener<Object, Object> {
    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        String token = (String) cacheEvent.getKey();
        switch (cacheEvent.getType()) {
            case CREATED: {// 添加了一个新的token (说明有一个用户刚登录)
                SysUserDto user = (SysUserDto) cacheEvent.getNewValue();
                // 以便将来通过用户id找到他对应的token
                Caches.put(user.getUser().getId(), token);
                break;
            }
            case REMOVED:
            case EXPIRED: { // token被移除或者过期了
                SysUserDto user = (SysUserDto) cacheEvent.getOldValue();
                Caches.remove(user.getUser().getId());
                break;
            }
            default:
                break;
        }
    }

}
