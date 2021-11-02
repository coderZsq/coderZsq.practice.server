package io.joyrpc.protocol.handler;

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

import io.joyrpc.constants.ExceptionCode;
import io.joyrpc.protocol.MessageHandler;
import io.joyrpc.protocol.message.Message;
import io.joyrpc.transport.channel.ChannelContext;
import io.joyrpc.util.network.Ipv4;
import org.slf4j.Logger;

/**
 * 抽象的请求处理器
 */
public abstract class AbstractReceiver implements MessageHandler {

    /**
     * 应答
     *
     * @param context  上下文
     * @param request  请求
     * @param response 应答
     * @param logger   日志
     */
    public static void acknowledge(final ChannelContext context, final Message request, final Message response, final Logger logger) {
        context.wrote(response).whenComplete((v, error) -> {
            if (error != null) {
                logger.error(String.format(" %s Failed to send error to remote %s for message id: %d Cause by:",
                        ExceptionCode.format(ExceptionCode.PROVIDER_SEND_MESSAGE_ERROR),
                        Ipv4.toAddress(context.getChannel().getRemoteAddress()),
                        request.getMsgId()),
                        error);
            }
        });
    }

}
