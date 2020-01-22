package main

import (
	"crypto/sha256"
	"fmt"

	"golang.org/x/crypto/ripemd160"
)

func main() {
	// sha256
	hash := sha256.New()
	hash.Write([]byte("coderZsq"))
	bytes := hash.Sum(nil)
	fmt.Printf("sha256: %x\n", bytes)
	// sha256: 66fbcaad330ccb47041ff59a158f449c8a6ffb5b863d815279c51303b5790cc3

	// ripemd160
	r160 := ripemd160.New()
	r160.Write(bytes)
	bytesRipemd := r160.Sum(nil)
	fmt.Printf("rpiemd160: %x\n", bytesRipemd)
}
