package blc

import (
	"fmt"
	"log"
	"math/big"
	"os"

	"github.com/boltdb/bolt"
)

// 区块链管理文件
// 数据库名称
const dbName = "block.db"

// 表名称
const blockTableName = "blocks"

// BlockChain 区块链基本结构
type BlockChain struct {
	//Blocks []*Block // 区块的切片
	DB  *bolt.DB // 数据库对象
	Tip []byte   // 保存最新区块的哈希值
}

// 判断数据库文件是否存在
func dbExist() bool {
	if _, err := os.Stat(dbName); os.IsNotExist(err) {
		// 数据库文件不存在
		return false
	}
	return true
}

// CreateBlockChainWithGenesisBlock 初始化区块链
func CreateBlockChainWithGenesisBlock(txs []*Transaction) *BlockChain {
	if dbExist() {
		// 文件已存在, 说明创世区块已存在
		fmt.Println("创世区块已存在...")
		os.Exit(1)
	}
	// 保存最新区块的哈希值
	var blockHash []byte
	// 1. 创建或者打开一个数据库
	db, err := bolt.Open(dbName, 0600, nil)
	if nil != err {
		log.Panicf("create db [%s] failed %v\n", dbName, err)
	}
	// 2. 创建桶, 把生成的创世区块存入数据库
	db.Update(func(tx *bolt.Tx) error {
		b := tx.Bucket([]byte(blockTableName))
		if b == nil {
			// 没找到桶
			b, err := tx.CreateBucket([]byte(blockTableName))
			if nil != err {
				log.Panicf("create bucket [%s] failed %v\n", blockTableName, err)
			}
			// 生成创世区块
			genesisBlock := CreateGenesisBlock(txs)
			// 存储
			// 1. key, value分别以什么数据代表==hash
			// 2. 如何把block结构存入到数据库中--序列化
			err = b.Put(genesisBlock.Hash, genesisBlock.Serialize())
			if nil != err {
				log.Panicf("insert the genesis block failed %v\n", err)
			}
			blockHash = genesisBlock.Hash
			// 存储最新区块的哈希
			// l : latest
			err = b.Put([]byte("l"), genesisBlock.Hash)
			if nil != err {
				log.Panicf("save the hash of genesis block failed %v\n", err)
			}
		}
		return nil
	})
	return &BlockChain{db, blockHash}
}

// AddBlock 添加区块到区块链中
func (bc *BlockChain) AddBlock(txs []*Transaction) {
	// 更新区块数据(insert)
	err := bc.DB.Update(func(tx *bolt.Tx) error {
		// 1. 获取数据库桶
		b := tx.Bucket([]byte(blockTableName))
		if nil != b {
			// 2. 获取最后插入的区块
			blockBytes := b.Get(bc.Tip)
			// 3. 区块数据反序列化
			latestBlock := DeserializeBlock(blockBytes)
			// 4. 新建区块
			// height int64, prevBlockHash []byte, data []byte
			newBlock := NewBlock(latestBlock.Height+1, latestBlock.Hash, txs)
			// 5. 存入数据库
			err := b.Put(newBlock.Hash, newBlock.Serialize())
			if nil != err {
				log.Panicf("insert the new block to db failed %v\n", err)
			}
			// 更新最新区块的哈希(数据库)
			err = b.Put([]byte("l"), newBlock.Hash)
			if nil != err {
				log.Panicf("update the latest block hash to db failed %v\n", err)
			}
			// 更新区块链对象中的最新区块哈希
			bc.Tip = newBlock.Hash
		}
		return nil
	})
	if nil != err {
		log.Panicf("insert block to db failed %v", err)
	}
}

// PrintChain 遍历数据库, 输出所有区块信息
func (bc *BlockChain) PrintChain() {
	// 读取数据库
	fmt.Println("区块链完整信息...")
	var curBlock *Block
	bcit := bc.Iterator() // 获取迭代器对象
	// 循环读取
	for {
		fmt.Println("-----------------------------------------------------------------------------------------------")
		curBlock = bcit.Next()
		fmt.Printf("\tHash: %x\n", curBlock.Hash)
		fmt.Printf("\tPrevBlockHash: %x\n", curBlock.PrevBlockHash)
		fmt.Printf("\tTimeStamp: %v\n", curBlock.TimeStamp)
		fmt.Printf("\tTxs: %v\n", curBlock.Txs)
		fmt.Printf("\tHeight: %d\n", curBlock.Height)
		fmt.Printf("\tNonce: %d\n", curBlock.Nonce)
		// 退出条件
		// 转换为big.int
		var hashInt big.Int
		hashInt.SetBytes(curBlock.PrevBlockHash)
		// 比较
		if big.NewInt(0).Cmp(&hashInt) == 0 {
			// 遍历到创世区块
			break
		}
	}
}

// BlockchainObject 获取一个blockchain对象
func BlockchainObject() *BlockChain {
	// 获取DB
	db, err := bolt.Open(dbName, 0600, nil)
	if nil != err {
		log.Panicf("open the db [%s] failed! %v\n", dbName, err)
	}
	// 获取Tip
	var tip []byte
	err = db.View(func(tx *bolt.Tx) error {
		b := tx.Bucket([]byte(blockTableName))
		if nil != b {
			tip = b.Get([]byte("l"))
		}
		return nil
	})
	if nil != err {
		log.Printf("get the blockchain object failed %v\n", err)
	}
	return &BlockChain{db, tip}
}
