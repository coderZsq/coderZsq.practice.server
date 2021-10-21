package main

import (
	"net"
	"time"
)

// DialTimeout acts like Dial for establishing the
// connection to the server, writing a command and reading a reply.
func Dial(network, address string) (net.Conn, error) {
	return nil, nil
}

// DialTimeout acts like Dial but takes timeouts for establishing the
// connection to the server, writing a command and reading a reply.
func DialTimeout(network, address string, connectTimeout, readTimeout, writeTimeout time.Duration) (net.Conn, error) {
	return nil, nil
}

// DialDatabase acts like Dial but takes database for establishing the
// connection to the server, writing a command and reading a reply.
func DialDatabase(network, address string, database int) (net.Conn, error) {
	return nil, nil
}

// DialPool
func DialPool()  {
	
}