package blc

// TestResetUTXO 重置 utxo table
func (cli *CLI) TestResetUTXO() {
	blockchain := BlockchainObject()
	defer blockchain.DB.Close()
	utxoSet := UTXOSet{blockchain}
	utxoSet.ResetUTXOSet()
}

// TestFindUTXOMap 查找
func (cli *CLI) TestFindUTXOMap() {

}
