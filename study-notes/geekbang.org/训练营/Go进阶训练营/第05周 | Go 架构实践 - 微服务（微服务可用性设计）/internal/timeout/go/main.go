package main

import (
	"context"
	"fmt"
	"io"
	"net"
	"time"
)

type asiiConn struct {
	conn         net.Conn
	writeTimeout time.Duration
	rw           io.Writer
}

type item interface {
}

func (c *asiiConn) Get(ctx context.Context, key string) (result *item, err error) {
	c.conn.SetWriteDeadline(shrinkDeadline(ctx, c.writeTimeout))
	if _, err := fmt.Fprintf(c.rw, "gets %s\r\n", key); err != nil {

	}
	return
}

func shrinkDeadline(ctx context.Context, timeout time.Duration) time.Time {
	return time.Time{}
}
