package blc

// TestResetUTXO 重置 utxo table
func (cli *CLI) TestResetUTXO(nodeID string) {
	blockchain := BlockchainObject(nodeID)
	defer blockchain.DB.Close()
	utxoSet := UTXOSet{blockchain}
	utxoSet.ResetUTXOSet()
}

// TestFindUTXOMap 查找
func (cli *CLI) TestFindUTXOMap() {

}
