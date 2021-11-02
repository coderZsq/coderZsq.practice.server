package io.joyrpc.example.service;

import io.joyrpc.example.service.vo.EchoData;
import io.joyrpc.example.service.vo.EchoRequest;
import io.joyrpc.example.service.vo.EchoResponse;
import io.joyrpc.example.service.vo.Java8TimeObj;

public interface DemoService {

    String sayHello(String str) throws Throwable;

    int test(int count);

    <T> T generic(T value);

    default String echo(String str) throws Throwable {
        return sayHello(str);
    }

    default Java8TimeObj echoJava8TimeObj(Java8TimeObj timeObj) {
        return timeObj;
    }

    default EchoResponse<EchoData> echoRequest(EchoRequest<EchoData> request) {
        return request == null ? null : new EchoResponse<>(request.getHeader(), request.getBody());
    }

    default EchoResponse echoRequestGeneric(EchoRequest request) {
        return request == null ? null : new EchoResponse<>(request.getHeader(), request.getBody());
    }

    static String hello(String v) {
        return v;
    }

    void echoCallback(EchoCallback callback);

    public static interface EchoCallback {

        boolean echo(String str);
    }
}
