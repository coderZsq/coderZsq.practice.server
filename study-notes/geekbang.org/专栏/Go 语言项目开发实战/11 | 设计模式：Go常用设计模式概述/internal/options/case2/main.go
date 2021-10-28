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

type ConnectionOptions struct {
	Caching bool
	Timeout time.Duration
}

func NewDefaultOptions() *ConnectionOptions {
	return &ConnectionOptions{
		Caching: defaultCaching,
		Timeout: defaultTimeout,
	}
}

// NewConnect creates a connection with options.
func NewConnect(addr string, opts *ConnectionOptions) (*Connection, error) {
	return &Connection{
		addr:    addr,
		cache:   opts.Caching,
		timeout: opts.Timeout,
	}, nil
}
