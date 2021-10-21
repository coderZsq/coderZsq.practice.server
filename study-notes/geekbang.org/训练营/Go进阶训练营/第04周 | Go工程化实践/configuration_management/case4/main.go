package main

import (
	"fmt"
	"net"
	"time"
)

type dialOptions struct {
	dial func(network, address string) (net.Conn, error)
}

// DialOption specifies an option for dialing a Redis server.
type DialOption struct {
	f func(*dialOptions)
}

// Dial connects to the Redis server at the given network and
// address using the specified options.
func Dial(network, address string, options ...DialOption) (net.Conn, error) {
	do := dialOptions{
		dial: net.Dial,
	}
	for _, option := range options {
		option.f(&do)
		do.dial(network, address)
	} // ...
	return nil, nil
}

func DialDatabase(database int) DialOption {
	return DialOption{
		f: func(options *dialOptions) {
			options.dial = func(network, address string) (net.Conn, error) {
				fmt.Println(network, address, database)
				return nil, nil
			}
		},
	}
}

func DialPassword(password string) DialOption {
	return DialOption{
		f: func(options *dialOptions) {
			options.dial = func(network, address string) (net.Conn, error) {
				fmt.Println(network, address, password)
				return nil, nil
			}
		},
	}
}

func DialReadTimeout(readTimeout time.Duration) DialOption {
	return DialOption{
		f: func(options *dialOptions) {
			options.dial = func(network, address string) (net.Conn, error) {
				fmt.Println(network, address, readTimeout)
				return nil, nil
			}
		},
	}
}

func main() {
	_, _ = Dial("tcp", "127.0.0.1:3389",
		DialDatabase(0),
		DialPassword("hello"),
		DialReadTimeout(10*time.Second))
}
