package blc

// 网络服务常量管理

// PROTOCOL 协议
const PROTOCOL = "tcp"

// CommandLength 命令长度
const CommandLength = 12

// 命令分类
const (
	// 验证当前节点末端区块是否是最新区块
	CmdVersion = "version"
	// 从最长链上获取区块
	CmdGetBlocks = "getblocks"
	// 向其他节点展示当前节点有哪些区块
	CmdInv = "inv"
	// 请求指定区块
	CmdGetData = "getdata"
	// 接收到新区块之后, 进行处理
	CmdBlock = "block"
)
