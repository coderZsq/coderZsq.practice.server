package main

import "fmt"

// leak is a buggy function. It launches a goroutine that
// blocks receiving from a channel. Nothing will ever be
// sent on that channel and the channel is never closed so
// that goroutine will be blocked forever.
func leak()  {
	ch := make(chan int)

	go func() {
		val := <-ch
		fmt.Println("We received a value: ", val)
	}()
}
