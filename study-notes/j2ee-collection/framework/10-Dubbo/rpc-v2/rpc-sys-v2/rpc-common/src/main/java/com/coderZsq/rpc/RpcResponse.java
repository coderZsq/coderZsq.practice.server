package com.coderZsq.rpc;

import lombok.*;

import java.io.Serializable;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
// RPC 通信消息的响应数据规则
public class RpcResponse implements Serializable {
    private static final long serialVersionUID = -8172979904575949025L;
    // 响应的消息id
    private String responseId;
    // 请求的消息id
    private String requestId;
    // 响应的消息是否成功
    private boolean success;
    // 响应的数据结果
    private Object result;
    // 如果有异常信息, 在该对象中记录异常信息
    private Throwable throwable;
}
