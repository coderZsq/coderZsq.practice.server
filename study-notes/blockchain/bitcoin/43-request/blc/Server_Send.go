package blc

import (
	"bytes"
	"fmt"
	"io"
	"log"
	"net"
)

// 请求发送文件

// 发送请求
func sendMessage(to string, msg []byte) {
	fmt.Println("向服务器发送请求...")
	// 1. 连接上服务器
	conn, err := net.Dial(PROTOCOL, to)
	if nil != err {
		log.Panicf("connect to server [%s] failed! %v\n", to, err)
	}
	defer conn.Close()
	// 要发送的数据
	_, err = io.Copy(conn, bytes.NewReader(msg))
	if nil != err {
		log.Panicf("add the data to conn failed! %v\n", err)
	}
}

// 区块链版本验证
func sendVersion(toAddress string, bc *BlockChain) {
	// 1. 获取当前节点的区块高度
	height := bc.GetHeight()
	// 2. 组装生成version
	versionData := Version{int(height), nodeAddress}
	// 3. 数据序列化
	data := gobEncode(versionData)
	// 4. 将命令与版本组装成完整的请求
	request := append(commandToBytes(CmdVersion), data...)
	// 5. 发送请求
	sendMessage(toAddress, request)
}

// 从指定节点同步数据
func sendGetBlocks(toAddress string) {
	// 1. 生成数据
	data := gobEncode(GetBlocks{nodeAddress})
	// 2. 组装数据
	request := append(commandToBytes(CmdGetBlocks), data...)
	// 3. 发送请求
	sendMessage(toAddress, request)
}

// 发送获取指定区块请求
func sendGetData(toAddress string, hash []byte) {
	// 1. 生成数据
	data := gobEncode(GetData{nodeAddress, hash})
	// 2. 组装数据
	request := append(commandToBytes(CmdGetData), data...)
	// 3. 发送请求
	sendMessage(toAddress, request)
}

// 向其他节点展示
func sendInv(toAddress string, hashes [][]byte) {
	// 1. 生成数据
	data := gobEncode(Inv{nodeAddress, hashes})
	// 2. 组装数据
	request := append(commandToBytes(CmdInv), data...)
	// 3. 发送请求
	sendMessage(toAddress, request)
}

// 发送区块信息
func sendBlock(toAddress string, block []byte) {
	// 1. 生成数据
	data := gobEncode(BlockData{nodeAddress, block})
	// 2. 组装数据
	request := append(commandToBytes(CmdBlock), data...)
	// 3. 发送请求
	sendMessage(toAddress, request)
}
