package io.joyrpc.cluster.distribution;

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

import io.joyrpc.cluster.Candidate;
import io.joyrpc.cluster.Node;
import io.joyrpc.extension.Extensible;
import io.joyrpc.extension.Prototype;
import io.joyrpc.extension.URL;
import io.joyrpc.protocol.message.Invocation;
import io.joyrpc.protocol.message.RequestMessage;

/**
 * 负载均衡接口
 */
@Extensible("loadBalance")
public interface LoadBalance extends Prototype {

    /**
     * 选择一个服务实例，不能修改候选者的服务列表
     *
     * @param candidate 候选服务实例列表
     * @param request   请求对象
     * @return Node 被选中的服务实例
     */
    Node select(Candidate candidate, RequestMessage<Invocation> request);

    /**
     * URL
     *
     * @param url
     */
    default void setUrl(final URL url) {
    }

    /**
     * 初始化
     */
    default void setup() {
    }

}
