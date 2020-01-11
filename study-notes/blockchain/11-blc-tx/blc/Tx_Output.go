package blc

// 交易的输出管理

// TxOutput 输出结构
type TxOutput struct {
	// 金额
	value int
	// 用户名(UTXO的所有者)
	ScriptPubkey string
}
