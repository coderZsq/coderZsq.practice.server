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

import io.joyrpc.transport.channel.ChannelChain;
import io.joyrpc.transport.codec.Codec;
import io.joyrpc.transport.message.Message;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @date: 2019/1/8
 */
public interface Protocol {
    /**
     * JOY
     */
    int JOY = 1;
    /**
     * DUBBO
     */
    int DUBBO = 3;
    /**
     * HTTP
     */
    int HTTP = 9;
    /**
     * GRPC
     */
    int GRPC = 10;
    /**
     * Json Rpc
     */
    int JSON_RPC = 11;

    /**
     * Joy插件顺序
     */
    int JOY_ORDER = 100;
    /**
     * HTTP插件顺序
     */
    int HTTP_ORDER = 120;
    /**
     * GRPC插件顺序
     */
    int GRPC_ORDER = 130;
    /**
     * DUBBO插件顺序
     */
    int DUBBO_ORDER = 140;
    /**
     * TELNET插件顺序
     */
    int TELNET_ORDER = Short.MAX_VALUE;


    /**
     * 构造处理链
     *
     * @return
     */
    ChannelChain buildChain();

    /**
     * 根据协议版本获取编解码器
     *
     * @return
     */
    Codec getCodec();

    /**
     * 获取协议的魔术字节数组
     *
     * @return
     */
    byte[] getMagicCode();

    /**
     * 请求消息协议转换
     *
     * @return
     */
    default MessageConverter inMessage() {
        return null;
    }

    /**
     * 应答消息协议转换
     *
     * @return
     */
    default MessageConverter outMessage() {
        return null;
    }


    /**
     * 进行消息转换
     */
    interface MessageConverter {

        /**
         * 压缩算法版本转换
         *
         * @return
         */
        default Function<Byte, Byte> compression() {
            return null;
        }

        /**
         * 校验版本转换
         *
         * @return
         */
        default Function<Byte, Byte> checksum() {
            return null;
        }

        /**
         * 序列化版本转换
         *
         * @return
         */
        default Function<Byte, Byte> serialization() {
            return null;
        }

        /**
         * 消息类型转换
         *
         * @return
         */
        default Function<Integer, Integer> messageType() {
            return null;
        }

        /**
         * 消息转换
         *
         * @return
         */
        default Function<Object, Object> message() {
            return null;
        }

        /**
         * 异常转换
         *
         * @return
         */
        default Function<Throwable, Throwable> exception() {
            return throwable -> throwable;
        }

        /**
         * 根据请求和应答结果进行结果转换
         *
         * @return
         */
        default BiFunction<Message, Object, Object> response() {
            return null;
        }

    }

    /**
     * 名称
     */
    class ProtocolVersion {
        /**
         * 名称
         */
        protected final String name;
        /**
         * 版本
         */
        protected final String version;

        /**
         * 构造函数
         *
         * @param name
         * @param version
         */
        public ProtocolVersion(String name, String version) {
            this(name, version, false);
        }

        /**
         * 构造函数
         *
         * @param name
         * @param version
         * @param higherFirst
         */
        public ProtocolVersion(String name, String version, boolean higherFirst) {
            this.name = name;
            this.version = version;
        }

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            ProtocolVersion that = (ProtocolVersion) o;
            return Objects.equals(name, that.name)
                    && Objects.equals(version, that.version);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, version);
        }
    }

}
