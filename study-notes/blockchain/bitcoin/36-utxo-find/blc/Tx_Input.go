package blc

import "bytes"

// 交易的输入管理

// TxInput 输入结构
type TxInput struct {
	// 交易哈希(不是指当前的交易哈希)
	TxHash []byte
	// 引用的上一笔交易的输出索引号
	Vout int
	// 数字签名
	Signature []byte
	// 公钥
	PublicKey []byte
}

// CheckPubkeyWithAddress 验证引用的地址是否匹配
//func (txInput *TxInput) CheckPubkeyWithAddress(address string) bool {
//	return address == txInput.ScriptSig
//}

// UnLockRipemd160Hash 传递哈希160进行判断
func (txInput *TxInput) UnLockRipemd160Hash(ripemd160Hash []byte) bool {
	// 获取input的ripemd160哈希值
	inputRipemd160Hash := Ripemd160Hash(txInput.PublicKey)
	return bytes.Compare(inputRipemd160Hash, ripemd160Hash) == 0
}
