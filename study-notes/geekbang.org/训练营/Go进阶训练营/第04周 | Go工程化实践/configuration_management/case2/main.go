package main

import (
	"net"
	"time"
)

// Config redis settings.
type Config struct {
	*Config
	Addr        string
	Auth        string
	DialTimeout time.Duration
	ReadTimout  time.Duration
	WriteTimout time.Duration
}

// NewConn new a redis conn.
func NewConn(c *Config) (cn net.Conn, err error) {
	return nil, nil
}

func main() {
	c := &Config{
		Addr: "tcp://127.0.0.1:3389",
	}
	_, _ = NewConn(c)
	c.Addr = "tcp://127.0.0.1:3390" // 副作用是什么?
}
