package blc

import "fmt"

// CreateWallets 创建钱包
func (cli *CLI) CreateWallets() {
	wallets := NewWallets() // 生成集合对象
	wallets.CreateWallet()
	fmt.Printf("wallets: %v\n", wallets.Wallets)
}
