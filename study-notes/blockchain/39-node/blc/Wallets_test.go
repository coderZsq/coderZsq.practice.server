package blc

import (
	"fmt"
	"testing"
)

func TestWallets_CreateWallet(t *testing.T) {
	wallets := NewWallets("3001") // 生成集合对象
	wallets.CreateWallet("3001")
	fmt.Printf("wallets: %v\n", wallets.Wallets)
}
