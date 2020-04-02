package blc

import (
	"fmt"
	"testing"
)

func TestWallets_CreateWallet(t *testing.T) {
	wallets := NewWallets() // 生成集合对象
	wallets.CreateWallet()
	fmt.Printf("wallets: %v\n", wallets.Wallets)
}
