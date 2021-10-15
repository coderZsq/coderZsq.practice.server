package main

import (
	"fmt"
	webv1 "geektime/toy-web/pkg/v1"
	"net/http"
)

func main() {
	server := webv1.NewSdkHttpServer("test-server")
	server.Route(http.MethodGet, "/user/signup", SignUp)
	err := server.Start(":8080")
	if err != nil {

	}
}

func SignUp(ctx *webv1.Context) {
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