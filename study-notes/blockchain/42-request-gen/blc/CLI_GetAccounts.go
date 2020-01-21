package blc

import "fmt"

// GetAccounts 获取地址列表
func (cli *CLI) GetAccounts(nodeID string) {
	wallets := NewWallets(nodeID)
	fmt.Println("账号列表")
	for key := range wallets.Wallets {
		fmt.Printf("\t[%s]\n", key)
	}
}
