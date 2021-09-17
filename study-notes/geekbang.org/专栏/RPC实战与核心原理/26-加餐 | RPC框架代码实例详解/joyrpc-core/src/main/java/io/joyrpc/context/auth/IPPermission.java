package io.joyrpc.context.auth;

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

import io.joyrpc.util.network.IpLong;
import io.joyrpc.util.network.Lan;

import java.util.Map;

/**
 * IP访问许可
 */
public class IPPermission {
    public static final String MASK = "*";
    /**
     * 是否启用
     */
    protected boolean enabled;
    /**
     * 别名的IP白名单
     */
    protected Map<String, Lan> whites;
    /**
     * 别名的IP黑名单
     */
    protected Map<String, Lan> blacks;
    /**
     * 默认白名单
     */
    protected Lan defWhite;
    /**
     * 默认黑名单
     */
    protected Lan defBlack;

    /**
     * 构造函数
     *
     * @param enabled 启用标识
     * @param whites  白名单
     * @param blacks  黑名单
     */
    public IPPermission(final boolean enabled, final Map<String, Lan> whites, final Map<String, Lan> blacks) {
        this.enabled = enabled;
        this.whites = whites;
        this.blacks = blacks;
        this.defWhite = whites == null ? null : whites.get(MASK);
        this.defBlack = blacks == null ? null : blacks.get(MASK);
    }

    /**
     * 是否允许
     *
     * @param alias 分组
     * @param ip    ip
     * @return 允许标识
     */
    public boolean permit(final String alias, final String ip) {
        if (!enabled) {
            return true;
        }
        Lan whiteLan = whites == null ? null : whites.getOrDefault(alias, defWhite);
        Lan blackLan = blacks == null ? null : blacks.getOrDefault(alias, defBlack);
        if (whiteLan == null) {
            return blackLan == null || !blackLan.contains(new IpLong(ip));
        } else if (blackLan == null) {
            return whiteLan.contains(new IpLong(ip));
        } else {
            IpLong v = new IpLong(ip);
            return whiteLan.contains(v) && !blackLan.contains(v);
        }
    }
}
