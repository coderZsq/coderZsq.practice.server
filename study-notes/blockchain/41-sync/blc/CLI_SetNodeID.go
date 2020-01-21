package blc

import (
	"fmt"
	"io/ioutil"
	"log"
	"os"
)

const envFile = "env.txt"

// SetNodeID 设置端口号(环境变量)
func (cli *CLI) SetNodeID(nodeID string) {
	if nodeID == "" {
		fmt.Println("Please set the node id...")
		os.Exit(1)
	}
	//err := os.Setenv("NODE_ID", nodeID)
	err := ioutil.WriteFile(envFile, []byte(nodeID), 0644)
	if nil != err {
		log.Fatalf("set envfailed! %v\n", err)
	}
}
