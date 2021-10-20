package main

import (
	"context"
	"errors"
	"fmt"
	"time"
)

// search simulates a function that finds a record based
// on a search term. It takes 200ms to perform this work.
func search(term string) (string, error) {
	time.Sleep(200 * time.Millisecond)
	return "some value", nil
}

// process is the work for the program. It fins a record
// then print it.
func process(term string) error {
	record, err := search(term)
	if err != nil {
		return err
	}

	fmt.Println("Received:", record)
	return nil
}

type result struct {
	record string
	err  error
}

func process2(term string) error {
	ch := make(chan result, 1)
	var ctx, _ = context.WithTimeout(context.Background(), 199 * time.Millisecond)
	// Launch a goroutine to find the record. Create a result
	// from the returned values to send through the channel.
	go func() {
		record, err := search(term)
		ch <- result{record, err}
	}()
	// Block waiting to either receive from the goroutine's
	// channel or for the context to be canceled.
	select {
	case <-ctx.Done():
		return errors.New("search canceled")
	case result := <-ch:
		if result.err != nil {
			return result.err
		}
		fmt.Println("Received:", result.record)
		return nil
	}
}

func main() {
	process2("...")
}
