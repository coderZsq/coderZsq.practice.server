package main

import (
	"fmt"
)

func handle() (int, error) {
	return 1, nil
}

func main() {
	i, err := handle()
	if err != nil {
		return
	}
	fmt.Println(i)
}
