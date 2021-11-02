package main

import (
	"fmt"
	"net/http"
)

func serveApp()  {
	mux := http.NewServeMux()
	mux.HandleFunc("/", func(resp http.ResponseWriter, request *http.Request) {
		fmt.Fprintln(resp, "Hello, QCon!")
	})
	http.ListenAndServe("0.0.0.0:8080", mux)
}

func serveDebug()  {
	http.ListenAndServe("127.0.0.1:8001", http.DefaultServeMux)
}

func main() {
	go serveDebug()
	serveApp()
}