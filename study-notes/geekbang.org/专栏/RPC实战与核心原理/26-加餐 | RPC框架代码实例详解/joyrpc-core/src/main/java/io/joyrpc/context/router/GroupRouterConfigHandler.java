package io.joyrpc.context.router;

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


import io.joyrpc.context.ConfigEventHandler;
import io.joyrpc.extension.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.joyrpc.Plugin.JSON;
import static io.joyrpc.constants.Constants.GLOBAL_SETTING;
import static io.joyrpc.constants.Constants.SETTING_MAP_PARAM_ALIAS;
import static io.joyrpc.context.ConfigEventHandler.BIZ_ORDER;
import static io.joyrpc.context.router.GroupRouterConfiguration.GROUP_ROUTER;


/**
 * 分组路由配置
 *
 * @date: 2019/6/21
 */
@Extension(value = "groupRouter", order = BIZ_ORDER)
public class GroupRouterConfigHandler implements ConfigEventHandler {

    private static final Logger logger = LoggerFactory.getLogger(GroupRouterConfigHandler.class);

    @Override
    public void handle(final String className, final Map<String, String> oldAttrs, final Map<String, String> newAttrs) {
        if (!GLOBAL_SETTING.equals(className)) {
            String oldAttr = oldAttrs.get(SETTING_MAP_PARAM_ALIAS);
            String newAttr = newAttrs.get(SETTING_MAP_PARAM_ALIAS);
            if (!Objects.equals(oldAttr, newAttr)) {
                try {
                    GROUP_ROUTER.update(className, parse(newAttr));
                } catch (Exception e) {
                    logger.error(String.format("Error occurs while parsing group router of %s. caused by %s", className, e.getMessage()), e);
                    //如果异常，则忽略掉本次更新
                }
            }
        }
    }

    @Override
    public String[] getKeys() {
        return new String[]{SETTING_MAP_PARAM_ALIAS};
    }

    /**
     * 解析
     *
     * @param value
     * @return
     */
    protected Map<String, Map<String, String>> parse(final String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        Map<String, String> config = JSON.get().parseObject(value, Map.class);
        Map<String, Map<String, String>> methodCfg = new HashMap<>();
        for (Map.Entry<String, String> entry : config.entrySet()) {
            String methodAndParam = entry.getKey();
            int split = methodAndParam.indexOf(":");
            if (split > 0) {
                String method = methodAndParam.substring(0, split);
                String param = methodAndParam.substring(split + 1);
                Map paramCfg = methodCfg.computeIfAbsent(method, key -> new HashMap<>());
                paramCfg.put(param, entry.getValue());
            }
        }
        return methodCfg;
    }
}
