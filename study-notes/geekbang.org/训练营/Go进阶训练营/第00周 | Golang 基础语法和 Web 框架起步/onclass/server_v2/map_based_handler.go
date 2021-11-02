package main

import "net/http"

type Routable interface {
	Route(method string, pattern string,
		handleFunc handlerFunc)
}

type Handler interface {
	ServeHTTP(c *Context)
	Routable
}

type HandlerBasedOnMap struct {
	// key 应该是 method + url
	handlers map[string]func(ctx *Context)
}

func (h *HandlerBasedOnMap) Route(
	method string,
	pattern string,
	handleFunc handlerFunc) {
	key := h.key(method, pattern)
	h.handlers[key] = handleFunc
}

func (h *HandlerBasedOnMap) ServeHTTP(c *Context) {
	key := h.key(c.R.Method, c.R.URL.Path)
	if handler, ok := h.handlers[key]; ok {
		handler(c)
	} else {
		c.W.WriteHeader(http.StatusNotFound)
		c.W.Write([]byte("Not Found"))
	}
}

func (h *HandlerBasedOnMap) key(method string, pattern string) string {
	return method + "#" + pattern
}

var _ Handler = &HandlerBasedOnMap{}

func NewHandlerBaseOnMap() Handler {
	return &HandlerBasedOnMap{
		handlers: make(map[string]func(ctx *Context)),
	}
}