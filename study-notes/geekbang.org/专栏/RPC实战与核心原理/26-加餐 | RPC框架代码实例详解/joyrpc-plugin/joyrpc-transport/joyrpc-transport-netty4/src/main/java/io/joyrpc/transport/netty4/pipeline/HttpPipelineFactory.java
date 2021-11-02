package io.joyrpc.transport.netty4.pipeline;

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

import io.joyrpc.extension.Extension;
import io.joyrpc.transport.channel.Channel;
import io.joyrpc.transport.codec.Codec;
import io.joyrpc.transport.netty4.http.HttpRequestNormalizer;
import io.joyrpc.transport.netty4.http.HttpResponseNormalizer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * http管道工厂
 */
@Extension("http")
public class HttpPipelineFactory extends AbstractPipelineFactory {

    @Override
    protected void build(final ChannelPipeline pipeline, final Codec codec, final Channel channel) {
        pipeline.addLast(DECODER, new HttpRequestDecoder());
        pipeline.addLast(HTTP_AGGREGATOR, new HttpObjectAggregator(65535));
        pipeline.addLast(ENCODER, new HttpResponseEncoder());
        pipeline.addLast(HTTP_REQUEST_NORMALIZER, new HttpRequestNormalizer());
        pipeline.addLast(HTTP_RESPONSE_NORMALIZER, new HttpResponseNormalizer());
    }
}
