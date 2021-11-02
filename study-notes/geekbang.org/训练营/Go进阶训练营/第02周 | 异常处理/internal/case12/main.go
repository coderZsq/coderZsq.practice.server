package main

import (
	"fmt"
	"os"
)

var path = ""

func init() {
	f, err := os.Open(path)
	if err != nil {
		// handle error
	}
	// do stuff
	fmt.Printf("%v\n", f)
}

func init() {
	f, err := os.Open(path)
	if err == nil {
		// do stuff
		fmt.Printf("%v\n", f)
	}
	// handle error
}
