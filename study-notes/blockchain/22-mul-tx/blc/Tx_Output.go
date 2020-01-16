package blc

// 交易的输出管理

// TxOutput 输出结构
type TxOutput struct {
	// 金额 (大写才能导出金额)
	Value int
	// 用户名(UTXO的所有者)
	ScriptPubkey string
}

// CheckPubkeyWithAddress 验证当前UTXO是否属于指定的地址
func (txOutput *TxOutput) CheckPubkeyWithAddress(address string) bool {
	return address == txOutput.ScriptPubkey
}
