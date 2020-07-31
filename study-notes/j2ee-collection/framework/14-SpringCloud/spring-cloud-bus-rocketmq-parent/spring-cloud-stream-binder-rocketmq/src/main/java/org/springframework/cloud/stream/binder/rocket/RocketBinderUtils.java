/*
 * Copyright (C) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.stream.binder.rocket;

import org.apache.rocketmq.spring.config.RocketMQProperties;
import org.springframework.cloud.stream.binder.rocket.properties.RocketBinderConfigurationProperties;
import org.springframework.util.StringUtils;

public class RocketBinderUtils {

    public static RocketBinderConfigurationProperties mergeProperties(
            RocketBinderConfigurationProperties rocketBinderConfigurationProperties,
            RocketMQProperties rocketMQProperties) {
        RocketBinderConfigurationProperties result = new RocketBinderConfigurationProperties();
        if (StringUtils.isEmpty(rocketMQProperties.getNameServer())) {
            result.setNameServer(rocketBinderConfigurationProperties.getNameServer());
        } else {
            result.setNameServer(rocketMQProperties.getNameServer());
        }
        return result;
    }

}
