package main

import (
	"./blc"
)

// 启动
// $ go build -o bc main.go
func main() {
	cli := blc.CLI{}
	cli.Run()
}
