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

// 查找

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
