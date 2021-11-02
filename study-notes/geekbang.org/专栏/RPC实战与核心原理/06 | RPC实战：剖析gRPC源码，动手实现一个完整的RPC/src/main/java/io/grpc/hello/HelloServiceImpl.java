package io.grpc.hello;

import io.grpc.stub.StreamObserver;

static class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void say(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}