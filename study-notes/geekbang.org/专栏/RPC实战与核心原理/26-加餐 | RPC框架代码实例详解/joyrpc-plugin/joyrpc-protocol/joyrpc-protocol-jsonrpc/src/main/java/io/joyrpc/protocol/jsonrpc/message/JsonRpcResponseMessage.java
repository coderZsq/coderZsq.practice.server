package io.joyrpc.protocol.jsonrpc.message;

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

import io.joyrpc.protocol.http.message.AbstractJsonResponseMessage;
import io.joyrpc.protocol.jsonrpc.exception.JsonRpcCodecException;
import io.joyrpc.protocol.message.MessageHeader;
import io.joyrpc.protocol.message.ResponsePayload;

import static io.joyrpc.Plugin.JSON;

/**
 * 应答
 */
public class JsonRpcResponseMessage extends AbstractJsonResponseMessage {
    /**
     * 版本
     */
    protected JsonRpcResponse jsonRpcResponse;

    public JsonRpcResponseMessage(JsonRpcResponse jsonRpcResponse) {
        this.jsonRpcResponse = jsonRpcResponse;
    }

    public JsonRpcResponseMessage(MessageHeader header, ResponsePayload response, JsonRpcResponse jsonRpcResponse) {
        super(header, response);
        this.jsonRpcResponse = jsonRpcResponse;
    }

    @Override
    protected void render() {
        if (!response.isError()) {
            jsonRpcResponse.setResult(response.getResponse());
        } else {
            Throwable exception = response.getException();
            if (exception instanceof JsonRpcCodecException) {
                JsonRpcCodecException jrce = (JsonRpcCodecException) exception;
                jsonRpcResponse.setError(new JsonRpcError(Integer.parseInt(jrce.getErrorCode()), exception.getMessage()));
            } else {
                jsonRpcResponse.setError(new JsonRpcError(-32603, exception.getMessage()));
            }
        }
        content = JSON.get().toJSONBytes(jsonRpcResponse);
        status = 200;
    }
}
