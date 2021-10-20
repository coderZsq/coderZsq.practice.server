package main

import (
	"fmt"
	"sync"
	"time"
)

var Wait sync.WaitGroup
var Counter int = 0

func main() {
	for routine := 1; routine <= 2; routine++ {
		Wait.Add(1)
		go Routine(routine)
	}
	Wait.Wait()
	fmt.Printf("Final Counter: %d\n", Counter)
}

func Routine(routine int) {
	for count := 0; count < 2; count++ {
		Counter = Counter + 1
		time.Sleep(1 * time.Nanosecond)
	}
	Wait.Done()
}
