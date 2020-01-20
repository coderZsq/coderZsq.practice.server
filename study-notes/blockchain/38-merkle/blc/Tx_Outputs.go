package blc

import (
	"bytes"
	"encoding/gob"
	"log"
)

// TxOutputs 存入所有输出的集合
type TxOutputs struct {
	TxOutputs []*TxOutput
}

// Serialize 输出结构的序列化
func (txOutputs *TxOutputs) Serialize() []byte {
	var result bytes.Buffer
	encoder := gob.NewEncoder(&result)
	if err := encoder.Encode(txOutputs); nil != err {
		log.Panicf("serialize the utxo failed! %v\n", err)
	}
	return result.Bytes()
}

// DeserializeTxOutputs 输出结构反序列化
func DeserializeTxOutputs(txOutputsBytes []byte) *TxOutputs {
	var txOutouts TxOutputs
	decoder := gob.NewDecoder(bytes.NewReader(txOutputsBytes))
	if err := decoder.Decode(&txOutouts); nil != err {
		log.Panicf("deserialize the struct utxo failed! %v\n", err)
	}
	return &txOutouts
}
