package main

import (
	"fmt"
	"net/http"
)

func home(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "这是主页")
}

func user(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "这是用户")
}

func createUser(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "这是创建用户")
}

func order(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "这是订单")
}

func main() {
	server := NewHttpServer("test-server")
	//server.Route("/", home)
	//server.Route("/user", user)
	//server.Route("/user/create", createUser)
	server.Route(http.MethodGet, "/user/signup", SignUp)
	//server.Route("/order", order)
	err := server.Start(":8080")
	if err != nil {
		panic(err)
	}
	// 后面还有很多事情
}
