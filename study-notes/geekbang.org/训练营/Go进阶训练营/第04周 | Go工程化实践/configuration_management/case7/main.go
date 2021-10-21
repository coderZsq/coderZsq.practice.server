package main

import (
	"context"
	"time"
)

type HelloRequest struct {
}

type HelloReply struct {
}

type GreeterClient interface {
	SayHello(ctx context.Context, in *HelloRequest, opts ...CallOption) (*HelloReply, error)
}

type callInfo struct {
}

type CallOption interface {
	before(*callInfo) error
	after(*callInfo)
}

// EmptyCallOption does not alter the Call configuration.
type EmptyCallOption struct{}

// TimeoutCallOption timeout option.
type TimeoutCallOption struct {
	EmptyCallOption
	Timeout time.Duration
}
