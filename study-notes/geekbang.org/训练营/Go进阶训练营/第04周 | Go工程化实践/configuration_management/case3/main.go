package main

import (
	"net"
	"time"
)

// Config redis settings.
type Config struct {
	*pool.Config
	Addr        string
	Auth        string
	DialTimeout time.Duration
	ReadTimout  time.Duration
	WriteTimout time.Duration
}

// NewConn new a redis conn.
func NewConn(c Config) (cn net.Conn, err error) {
	return nil, nil
}

// NewConn new a redis conn.
func NewConn(c *Config) (cn net.Conn, err error) {
	return nil, nil
}

// NewConn new a redis conn.
func NewConn(c ...*Config) (cn net.Conn, err error) {
	return nil, nil
}

func main() {
	log.Init(nil) // 这样使用默认配置
	// config.fix() // 修正默认配置
}

// "I believe that we, as Go programmers, should work hard to ensure that nil is never a parameter that needs to be passed to any public function." --Dave Cheney
