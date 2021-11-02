package main

import (
	"fmt"
	"net"
	"time"
)

// options configures how we set up the
// connection.
type Option interface {
	apply(*options)
}

type options struct {
	dial func(network, address string) (net.Conn, error)
}

// Dial connects to the Redis server at the given network and
// address using the specified options
func Dial(network, address string, ops ...Option) (net.Conn, error) {
	do := options{
		dial: net.Dial,
	}
	for _, option := range ops {
		option.apply(&do)
		_, _ = do.dial(network, address)
	} // ...
	return nil, nil
}

type Database struct {
	Database int
}

func (db *Database) apply(options *options) {
	options.dial = func(network, address string) (net.Conn, error) {
		fmt.Println(network, address, db.Database)
		return nil, nil
	}
}

func DialDatabase(database int) Option {
	return &Database{database}
}

type Password struct {
	Password string
}

func (ps *Password) apply(options *options) {
	options.dial = func(network, address string) (net.Conn, error) {
		fmt.Println(network, address, ps.Password)
		return nil, nil
	}
}

func DialPassword(password string) Option {
	return &Password{password}
}

type ReadTimeout struct {
	ReadTimeout time.Duration
}

func (rt *ReadTimeout) apply(options *options) {
	options.dial = func(network, address string) (net.Conn, error) {
		fmt.Println(network, address, rt.ReadTimeout)
		return nil, nil
	}
}

func DialReadTimeout(readTimeout time.Duration) Option {
	return &ReadTimeout{readTimeout}
}

type Config struct {
	Network     string
	Addr        string
	Database    int
	Password    string
	ReadTimeout time.Duration
}

// options apply config to options.
func (c *Config) Options() []Option {
	return []Option{
		DialDatabase(c.Database),
		DialPassword(c.Password),
		DialReadTimeout(c.ReadTimeout),
	}
}

func main() {
	// instead use load yaml file.
	c := &Config{
		Network: "tcp",
		Addr: "127.0.0.1:3389",
		Database: 1,
		Password: "Hello",
		ReadTimeout: 1 * time.Second,
	}
	r, _ := Dial(c.Network, c.Addr, c.Options()...)
	fmt.Println(r)
}
