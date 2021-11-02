package io.joyrpc.cache.caffeine;

/*-
 * #%L
 * joyrpc
 * %%
 * Copyright (C) 2019 joyrpc.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.benmanes.caffeine.cache.Caffeine;
import io.joyrpc.cache.Cache;
import io.joyrpc.cache.CacheConfig;
import io.joyrpc.cache.CacheFactory;
import io.joyrpc.cache.CacheObject;
import io.joyrpc.extension.Extension;
import io.joyrpc.extension.condition.ConditionalOnClass;

import java.util.concurrent.TimeUnit;

import static io.joyrpc.cache.CacheFactory.CAFFEINE_ORDER;

/**
 * CaffeineCache实现
 */
@Extension(value = "caffeine", provider = "benmanes", order = CAFFEINE_ORDER)
@ConditionalOnClass("com.github.benmanes.caffeine.cache.Caffeine")
public class CaffeineCacheFactory implements CacheFactory {
    @Override
    public <K, V> Cache<K, V> build(final String name, final CacheConfig<K, V> config) {
        Caffeine<Object, Object> builder = Caffeine.newBuilder();
        if (config.getExpireAfterWrite() > 0) {
            builder.expireAfterWrite(config.getExpireAfterWrite(), TimeUnit.MILLISECONDS);
        }
        builder.maximumSize(config.getCapacity() > 0 ? config.getCapacity() : Long.MAX_VALUE);
        com.github.benmanes.caffeine.cache.Cache<K, CacheObject<V>> cache = builder.build();
        return new CaffeineCache<>(cache, config);
    }
}
