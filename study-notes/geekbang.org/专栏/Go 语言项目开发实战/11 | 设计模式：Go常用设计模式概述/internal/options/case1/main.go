package options

import (
	"time"
)

const (
	defaultTimeout = 10
	defaultCaching = false
)

type Connection struct {
	addr    string
	cache   bool
	timeout time.Duration
}

// NewConnect creates a connection.
func NewConnect(addr string) (*Connection, error) {
	return &Connection{
		addr:    addr,
		cache:   defaultCaching,
		timeout: defaultTimeout,
	}, nil
}

// NewConnectWithOptions creates a connection with options.
func NewConnectWithOptions(addr string, cache bool, timeout time.Duration) (*Connection, error) {
	return &Connection{
		addr:    addr,
		cache:   cache,
		timeout: timeout,
	}, nil
}
