package io.joyrpc.filter.provider;

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

import io.joyrpc.constants.Constants;
import io.joyrpc.extension.Extension;
import io.joyrpc.filter.AbstractTraceFilter;
import io.joyrpc.filter.ConsumerFilter;
import io.joyrpc.filter.ProviderFilter;
import io.joyrpc.protocol.message.Invocation;
import io.joyrpc.protocol.message.RequestMessage;
import io.joyrpc.util.network.Ipv4;

import java.util.Map;

import static io.joyrpc.util.Maps.put;

/**
 * 服务提供者APM过滤器
 */
@Extension(value = "trace", order = ConsumerFilter.TRACE_ORDER)
public class TraceFilter extends AbstractTraceFilter implements ProviderFilter {

    @Override
    protected void createTags(final RequestMessage<Invocation> request, final Map<String, String> tags) {
        super.createTags(request, tags);
        Invocation invocation = request.getPayLoad();
        put(tags, COMPONENT_TAG, component);
        put(tags, SPAN_KIND_TAG, "server");
        put(tags, CLIENT_ALIAS_TAG, invocation.getAlias());
        put(tags, CLIENT_NAME_TAG, invocation.getAttachment(Constants.HIDDEN_KEY_APPNAME));
        put(tags, CLIENT_ADDRESS_TAG, Ipv4.toAddress(request.getRemoteAddress()));
        put(tags, SERVER_ADDRESS_TAG, Ipv4.toAddress(request.getLocalAddress()));
        put(tags, CLIENT_RETRY_TAG, request.getRetryTimes() <= 0 ? null : String.valueOf(request.getRetryTimes()));
    }
}
