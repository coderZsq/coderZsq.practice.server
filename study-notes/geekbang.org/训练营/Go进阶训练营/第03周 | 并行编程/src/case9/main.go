package main

import (
	"context"
	"fmt"
	"net/http"
)

type serverHandler struct {
}

func (sh *serverHandler) ServeHTTP(resp http.ResponseWriter, req *http.Request) {
	fmt.Fprintln(resp, "Hello, QCon!")
}

func NewServerHandler() http.Handler {
	return &serverHandler{}
}

func serveApp(stop <-chan struct{}) error {
	return serve("0.0.0.0:8080", NewServerHandler(), stop)
}

func serveDebug(stop <-chan struct{}) error {
	return serve("127.0.0.1:8001", NewServerHandler(), stop)
}

func serve(addr string, handler http.Handler, stop <-chan struct{}) error {
	s := http.Server{
		Addr:    addr,
		Handler: handler,
	}

	go func() {
		<-stop // wait for stop signal
		s.Shutdown(context.Background())
	}()

	return s.ListenAndServe()
}

func main() {
	done := make(chan error, 2)
	stop := make(chan struct{})
	go func() {
		done <- serveDebug(stop)
	}()
	go func() {
		done <- serveApp(stop)
	}()

	var stopped bool
	for i := 0; i < cap(done); i++ {
		if err := <-done; err != nil {
			fmt.Println("error: %v", err)
		}
		if !stopped {
			stopped = true
			close(stop)
		}
	}
}
