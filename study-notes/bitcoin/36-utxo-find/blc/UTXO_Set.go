package blc

import (
	"encoding/hex"
	"fmt"
	"log"

	"github.com/boltdb/bolt"
)

// UTXO持久化相关管理

// 用于存放utxo的bucket
const utxoTableName = "utxoTable"

// UTXOSet utxoSet结构(保存指定区块链中所有的UTXO)
type UTXOSet struct {
	BlockChain *BlockChain
}

// 更新

// GetBalance 查询余额
func (utxoSet *UTXOSet) GetBalance(address string) int {
	UTXOS := utxoSet.FindUTXOWithAddress(address)
	var amount int
	for _, utxo := range UTXOS {
		fmt.Printf("utxo-txhash: %x\n", utxo.TxHash)
		fmt.Printf("utxo-index: %x\n", utxo.Index)
		fmt.Printf("utxo-ripemd160hash: %x\n", utxo.Output.Ripemd160Hash)
		fmt.Printf("utxo-value: %x\n", utxo.Output.Value)
		amount += utxo.Output.Value
	}
	return amount
}

// FindUTXOWithAddress 查找
func (utxoSet *UTXOSet) FindUTXOWithAddress(address string) []*UTXO {
	var utxos []*UTXO
	err := utxoSet.BlockChain.DB.View(func(tx *bolt.Tx) error {
		// 1. 获取utxotable表
		b := tx.Bucket([]byte(utxoTableName))
		if nil != b {
			// cursor -- 游标
			c := b.Cursor()
			// 通过游标遍历boltdb数据库中的数据
			for k, v := c.First(); k != nil; k, v = c.Next() {
				txOutputs := DeserializeTxOutputs(v)
				for _, utxo := range txOutputs.TxOutputs {
					if utxo.UnLockScriptPubkeyWithAddress(address) {
						utxoSingle := UTXO{Output: utxo}
						utxos = append(utxos, &utxoSingle)
					}
				}
			}
		}
		return nil
	})
	if nil != err {
		log.Panicf("find the utxo of [%s] failed! %v\n", address, err)
	}
	return utxos
}

// ResetUTXOSet 重置
func (utxoSet *UTXOSet) ResetUTXOSet() {
	// 在第一次创建的时候就更新utxo table
	utxoSet.BlockChain.DB.Update(func(tx *bolt.Tx) error {
		// 查找utxo table
		b := tx.Bucket([]byte(utxoTableName))
		if nil != b {
			err := tx.DeleteBucket([]byte(utxoTableName))
			if nil != err {
				log.Panicf("delete the utxo table failed! %v\n", err)
			}
		}
		// 创建
		bucket, err := tx.CreateBucket([]byte(utxoTableName))
		if nil != err {
			log.Panicf("create bucket failed! %v\n", err)
		}
		if nil != bucket {
			// 查找当前所有UTXO
			txOutputMap := utxoSet.BlockChain.FindUTXOMap()
			for keyHash, outputs := range txOutputMap {
				// 将所有UTXO存入
				txHash, _ := hex.DecodeString(keyHash)
				fmt.Printf("txHash: %x\n", txHash)
				// 存入utxo table
				err := bucket.Put(txHash, outputs.Serialize())
				if nil != err {
					log.Panicf("put the utxo into table failed! %v\n", err)
				}
			}
		}
		return nil
	})
}
