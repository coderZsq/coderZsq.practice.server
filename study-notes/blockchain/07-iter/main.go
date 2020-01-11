package main

import (
	"./blc"
)

// 启动
func main() {
	bc := blc.CreateBlockChainWithGenesisBlock()
	//fmt.Printf("blockchain: %v\n", bc.Blocks[0])
	bc.AddBlock([]byte("a send 100 eth to b"))
	bc.AddBlock([]byte("b send 100 eth to c"))
	bc.PrintChain()
}
