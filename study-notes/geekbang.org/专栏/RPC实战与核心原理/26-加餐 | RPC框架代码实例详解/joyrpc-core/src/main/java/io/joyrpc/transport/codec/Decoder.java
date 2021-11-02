package io.joyrpc.transport.codec;

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

import io.joyrpc.exception.CodecException;
import io.joyrpc.transport.buffer.ChannelBuffer;

/**
 * 解码器
 */
public interface Decoder {

    /**
     * 解码
     *
     * @param context 上下文
     * @param buffer  通道缓冲区
     * @return 解码后的对象
     * @throws CodecException
     */
    Object decode(DecodeContext context, ChannelBuffer buffer) throws CodecException;

}
