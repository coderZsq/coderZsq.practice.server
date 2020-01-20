package blc

import (
	"bytes"
	"encoding/binary"
	"encoding/json"
	"log"
	"os"
)

// IsValidArgs 参数数量检测函数
func IsValidArgs() {
	if len(os.Args) < 2 {
		PrintUsage()
		// 直接退出
		os.Exit(1)
	}
}

// IntToHex 实现int64转[]byte
func IntToHex(data int64) []byte {
	buffer := new(bytes.Buffer)
	err := binary.Write(buffer, binary.BigEndian, data)
	if nil != err {
		log.Panicf("int transact to []byte failed! %v\n", err)
	}
	return buffer.Bytes()
}

// JSONToSlice 标准JSON格式转切片
// mac下需要添加引号
// $ ./bc send -from "[\"coderZsq\"]" -to "[\"Alice\"]" -amount "[\"5\"]"
// $ ./bc send -from "[\"coderZsq\", \"Alice\"]" -to "[\"Alice\", \"coderZsq\"]" -amount "[\"5\", \"2\"]"
// coderZsq -> alice 5 --> alice 5, coderZsq 5
// alice -> coderZsq 2 --> alice 3, coderZsq 7
// 账号列表
// [1eTpS2Xtwhrf1QKpjV1RAHZMUR1jAxvD4k]
// [1nmZSKh768eamnt53w9yycHHXewkWK9dWM]
// [1fQWSofsgZLEG57jRu7JxyrpvFD3sQ27eY]
// [1pagYE3VwBEFLUdFjywMZkbi9Ndp3idY3z]
// $ ./bc send -from "[\"1eTpS2Xtwhrf1QKpjV1RAHZMUR1jAxvD4k\"]" -to "[\"1nmZSKh768eamnt53w9yycHHXewkWK9dWM\"]" -amount "[\"2\"]"
func JSONToSlice(jsonString string) []string {
	var strSlice []string
	// json
	if err := json.Unmarshal([]byte(jsonString), &strSlice); nil != err {
		log.Printf("json to []string failed! %v\n", err)
	}
	return strSlice
}

// StringToHash160 string转hash160
func StringToHash160(address string) []byte {
	pubKeyHash := Base58Decode([]byte(address))
	hash160 := pubKeyHash[:len(pubKeyHash)-addressCheckSumLen]
	return hash160
}
