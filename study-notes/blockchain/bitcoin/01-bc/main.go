package main

import (
	"fmt"

	"./blc"
)

// 启动
func main() {
	block := blc.NewBlock(1, nil, []byte("the first block testing"))
	fmt.Printf("the first block : %v\n", block)
}
