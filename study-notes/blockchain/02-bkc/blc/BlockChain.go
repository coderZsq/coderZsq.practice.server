package blc

// 区块链管理文件

// BlockChain 区块链基本结构
type BlockChain struct {
	Blocks []*Block // 区块的切片
}

// 初始化区块链

// AddBlock 添加区块到区块链中
func (bc *BlockChain) AddBlock(height int64, prevBlockHash []byte, data []byte) {
	//var newBlock *Block
	newBlock := NewBlock(height, prevBlockHash, data)
	bc.Blocks = append(bc.Blocks, newBlock)
}
