package blc

// 交易的输入管理

// TxInput 输入结构
type TxInput struct {
	// 交易哈希(不是指当前的交易哈希)
	TxHash []byte
	// 引用的上一笔交易的输出索引号
	Vout int
	// 用户名
	ScriptSig string
}
