package io.joyrpc.protocol.message;

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

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 应答消息
 *
 * @date: 2019/3/29
 * @date 2019-04-23 07:15
 */
public class ResponsePayload implements Serializable {

    public static final String RES_CLASS = "class";

    public static final String RESPONSE = "response";

    public static final String EXCEPTION = "exception";

    private static final long serialVersionUID = -7478498897807443823L;
    /**
     * 响应结果
     */
    protected Object response;
    /**
     * 异常信息
     */
    protected Throwable exception;
    /**
     * 返回值泛型
     */
    protected transient Type type;

    /**
     * 默认构造函数
     */
    public ResponsePayload() {
    }


    /**
     * 构造返回值消息
     *
     * @param response 返回值
     */
    public ResponsePayload(Object response) {
        this.response = response;
    }

    /**
     * 构造异常应答
     *
     * @param exception 异常
     */
    public ResponsePayload(Throwable exception) {
        this.exception = exception;
    }

    /**
     * 构造函数
     *
     * @param response  返回值
     * @param exception 异常
     */
    public ResponsePayload(Object response, Throwable exception) {
        this.response = response;
        this.exception = exception;
    }

    /**
     * 构造方法
     *
     * @param response  返回值
     * @param exception 异常
     * @param type      返回值类型
     */
    public ResponsePayload(Object response, Throwable exception, Type type) {
        this.response = response;
        this.exception = exception;
        this.type = type;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return the error
     */
    public boolean isError() {
        return exception != null;
    }

}
