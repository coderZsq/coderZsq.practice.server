package main

import (
	"fmt"
	"net/http"
)

type Server interface {
	// Route 设定一个路由, 命中该路由的会执行handlerFunc的代码
	// method POST, GET, PUT
	Route(method string, pattern string, handleFunc func(ctx *Context))

	// Start 启动我们的服务器
	Start(address string) error
}

// sdkHttpServer 这个是基于 net/http 这个包实现的 http server
type sdkHttpServer struct {
	// Name server 的名字, 给个标记, 日志输出的时候用得上
	Name string
	handler *HandlerBasedOnMap
}

// Route 注册路由
func (s *sdkHttpServer) Route(
	method string,
	pattern string,
	handleFunc func(ctx *Context)) {

	key := s.handler.key(method, pattern)
	s.handler.handlers[key] = handleFunc

}

func (s *sdkHttpServer) Start(address string) error {
	http.Handle("/", s.handler)
	return http.ListenAndServe(address, nil)
}

func NewHttpServer(name string) Server {
	return &sdkHttpServer{
		Name: name,
	}
}

func SignUp(ctx *Context) {
	req := &signUpReq{}

	err := ctx.ReadJson(req)
	if err != nil {
		ctx.BadRequestJson(err)
		return
	}

	resp := &commonResponse{
		Data: 123,
	}

	err = ctx.WriteJson(http.StatusOK, resp)
	if err != nil {
		fmt.Printf("写入响应失败: %v", err)
	}
}

type signUpReq struct {
	// Tag
	Email             string `json:"email"`
	Password          string `json:"password"`
	ConfirmedPassword string `json:"confirmed_password"`
}

type commonResponse struct {
	BizCode int         `json:"biz_code"`
	Msg     string      `json:"msg"`
	Data    interface{} `json:"data"`
}
