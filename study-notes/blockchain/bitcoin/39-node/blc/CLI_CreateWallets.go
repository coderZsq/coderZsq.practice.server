package blc

import "fmt"

// CreateWallets 命令行创建钱包集合
func (cli *CLI) CreateWallets(nodeID string) {
	// 创建一个钱包集合对象
	wallets := NewWallets(nodeID)
	wallets.CreateWallet(nodeID)
	fmt.Printf("wallets: %v\n", wallets)
}
