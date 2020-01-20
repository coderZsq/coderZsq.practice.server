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

// 更新
func (utxoSet *UTXOSet) update() {
	// 获取最新区块
	latestBlock := utxoSet.BlockChain.Iterator().Next()
	utxoSet.BlockChain.DB.Update(func(tx *bolt.Tx) error {
		b := tx.Bucket([]byte(utxoTableName))
		if nil != b {
			// 只需查找最新一个区块的交易列表, 因为每上链一个区块
			// utxo table都更新一次, 所以只需要查找最近一个区块中的交易
			for _, tx := range latestBlock.Txs {
				if !tx.IsCoinbaseTransaction() {
					// 2. 将已经被当前这笔交易的输入所引用的UTXO删掉
					for _, vin := range tx.Vins {
						// 需要更新的输出
						updatedOutputs := TxOutputs{}
						// 获取指定输入所引用的交易哈希的输出
						outputBytes := b.Get(vin.TxHash)
						// 输出列表
						outs := DeserializeTxOutputs(outputBytes)
						for outIdx, out := range outs.TxOutputs {
							if vin.Vout != outIdx {
								updatedOutputs.TxOutputs = append(updatedOutputs.TxOutputs, out)
							}
						}
						// 如果交易中没有UTXO了, 删除该交易
						if len(updatedOutputs.TxOutputs) == 0 {
							b.Delete(vin.TxHash)
						} else {
							// 将更新之后的utxo数据存入数据库
							b.Put(vin.TxHash, updatedOutputs.Serialize())
						}
					}
				}
				// 获取当前区块新生成的交易输出
				// 1. 将最新区块中的UTXO插入
				newOutputs := TxOutputs{}
				newOutputs.TxOutputs = append(newOutputs.TxOutputs, tx.Vouts...)
				b.Put(tx.TxHash, newOutputs.Serialize())
			}
		}
		return nil
	})
}
