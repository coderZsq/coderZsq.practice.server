package main

import (
	"net"
	"time"
)

type DialOption struct {

}

// Dial connects to the Redis server at the given network and
// address using the specified options.
func Dial(network, address string, options ...DialOption) (net.Conn, error) {
	return nil, nil
}

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
