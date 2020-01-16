package main

import (
	"fmt"

	"./blc"
)

func main() {
	result := blc.Base58Encode([]byte("this is the example"))
	fmt.Printf("result: %s\n", result)
	decodeResult := blc.Base58Decode([]byte("1nj2SLMErZakmBni8xhSXtimREn"))
	fmt.Printf("decode result: %s\n", decodeResult)
}
