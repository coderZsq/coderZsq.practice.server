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

import io.joyrpc.cluster.event.OfflineEvent;
import io.joyrpc.exception.HandlerException;
import io.joyrpc.protocol.MsgType;
import io.joyrpc.protocol.message.Message;
import io.joyrpc.transport.channel.ChannelContext;

/**
 * 下线请求处理器
 */
public class OfflineReceiver extends AbstractReceiver {

    @Override
    public void handle(final ChannelContext context, final Message message) throws HandlerException {
        context.publish(new OfflineEvent(context.getChannel()));
    }

    @Override
    public Integer type() {
        return (int) MsgType.OfflineReq.getType();
    }
}
