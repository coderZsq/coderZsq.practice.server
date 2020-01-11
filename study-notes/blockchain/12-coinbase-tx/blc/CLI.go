package blc

import (
	"flag"
	"fmt"
	"log"
	"os"
)

// 对blockchain的命令行操作进行管理

// CLI client对象
type CLI struct {
	//BC *BlockChain // blockchain对象
}

// PrintUsage 用法提示
func PrintUsage() {
	fmt.Println("Usage: ")
	// 初始化区块链
	fmt.Printf("\tcreateblockchain -address address -- 创建区块链\n")
	// 添加区块
	fmt.Printf("\taddblock -data DATA -- 添加区块\n")
	// 打印完整的区块信息
	fmt.Printf("\tprintchain -- 输出区块链信息\n")
}

// 初始化区块链
func (cli *CLI) createBlockchain(address string) {
	CreateBlockChainWithGenesisBlock(address)
}

// 添加区块
func (cli *CLI) addBlock(txs []*Transaction) {
	// 判断数据库是否存在
	if !dbExist() {
		fmt.Println("数据库不存在...")
		os.Exit(1)
	}
	blockchain := BlockchainObject()
	// 获取到blockchain的对象实例
	blockchain.AddBlock(txs)
}

// 打印完整区块链信息
func (cli *CLI) printchain() {
	if !dbExist() {
		fmt.Println("数据库不存在...")
		os.Exit(1)
	}
	blockchain := BlockchainObject()
	blockchain.PrintChain()
}

// IsValidArgs 参数数量检测函数
func IsValidArgs() {
	if len(os.Args) < 2 {
		PrintUsage()
		// 直接退出
		os.Exit(1)
	}
}

// Run 命令行运行函数
func (cli *CLI) Run() {
	// 检测参数数量
	IsValidArgs()
	// 新建相关命令
	// 添加区块
	addBlockCmd := flag.NewFlagSet("addblock", flag.ExitOnError)
	// 输出区块链完整信息
	printChainCmd := flag.NewFlagSet("printchain", flag.ExitOnError)
	// 创建区块链
	createBLCWithGenesisBlockCmd := flag.NewFlagSet("createblockchain", flag.ExitOnError)

	// 数据参数处理
	// 添加区块
	flagAddBlockArg := addBlockCmd.String("data", "sent 100 btc to player", "添加区块数据")
	// 创建区块链时指定的矿工地址(接收奖励)
	flagCreateBlockchainArg := createBLCWithGenesisBlockCmd.String("address", "coderZsq", "指定接收系统奖励的矿工地址")
	// 判断命令
	switch os.Args[1] {
	case "addblock":
		if err := addBlockCmd.Parse(os.Args[2:]); nil != err {
			log.Panicf("parse addBlockCmd failed! %v\n", err)
		}
	case "printchain":
		if err := printChainCmd.Parse(os.Args[2:]); nil != err {
			log.Panicf("parse printChainCmd failed! %v\n", err)
		}
	case "createblockchain":
		if err := createBLCWithGenesisBlockCmd.Parse(os.Args[2:]); nil != err {
			log.Panicf("parse createBLCWithGenesisBlockCmd failed! %v\n", err)
		}
	default:
		// 没有传递任何命令或者传递的命令不在上面的命令列表之中
		PrintUsage()
		os.Exit(1)
	}

	// 添加区块命令
	if addBlockCmd.Parsed() {
		if *flagAddBlockArg == "" {
			PrintUsage()
			os.Exit(1)
		}
		// 调用
		cli.addBlock([]*Transaction{})
	}

	// 输出区块链信息
	if printChainCmd.Parsed() {
		cli.printchain()
	}
	// 创建区块链命令
	if createBLCWithGenesisBlockCmd.Parsed() {
		if *flagCreateBlockchainArg == "" {
			PrintUsage()
			os.Exit(1)
		}
		cli.createBlockchain(*flagCreateBlockchainArg)
	}
}
