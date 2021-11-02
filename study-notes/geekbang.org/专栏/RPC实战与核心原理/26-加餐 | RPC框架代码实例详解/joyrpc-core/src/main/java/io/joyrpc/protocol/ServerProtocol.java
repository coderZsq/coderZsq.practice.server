package io.joyrpc.protocol;

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

import io.joyrpc.extension.Extensible;
import io.joyrpc.extension.URL;
import io.joyrpc.transport.buffer.ChannelBuffer;
import io.joyrpc.transport.message.Message;
import io.joyrpc.transport.session.Session;

import static io.joyrpc.transport.session.Session.AUTH_SESSION_NONE;

/**
 * 服务协议
 */
@Extensible("serverProtocol")
public interface ServerProtocol extends Protocol {

    /**
     * 判断当前数据包是否是该协议的，不移动缓冲区位置
     *
     * @param channelBuffer 连接通道缓冲区
     * @return 匹配标识
     */
    default boolean match(final ChannelBuffer channelBuffer) {
        byte[] magicCodes = getMagicCode();
        if (magicCodes == null || magicCodes.length == 0) {
            return false;
        }
        int length = magicCodes.length;
        if (channelBuffer.readableBytes() < length) {
            return false;
        }
        int readerIndex = channelBuffer.readerIndex();
        byte aByte;
        for (int i = 0; i < length; i++) {
            //不移动位置
            aByte = channelBuffer.getByte(readerIndex + i);
            if (magicCodes[i] != aByte) {
                return false;
            }
        }
        return true;
    }

    /**
     * 认证会话
     *
     * @param session 会话
     * @return 判断会话是否认证成功
     */
    default int authenticate(final Session session) {
        return AUTH_SESSION_NONE;
    }

    /**
     * 下线消息
     *
     * @param url url
     * @return 下线消息
     */
    default Message offline(final URL url) {
        return null;
    }
}
