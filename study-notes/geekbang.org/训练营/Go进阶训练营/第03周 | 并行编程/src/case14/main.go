package main

import (
	"context"
	"fmt"
	"time"
)

func main() {
	tr := NewTracker()
	go tr.Run()
	_ = tr.Event(context.Background(), "test1")
	_ = tr.Event(context.Background(), "test2")
	_ = tr.Event(context.Background(), "test3")
	time.Sleep(3 * time.Second)
	ctx, cancel := context.WithDeadline(context.Background(), time.Now().Add(5 * time.Second))
	defer cancel()
	tr.Shutdown(ctx)
}

func NewTracker() *Tracker {
	return &Tracker{
		ch: make(chan string, 10),
	}
}

// Tracker knows how to track events for the application.
type Tracker struct {
	ch   chan string
	stop chan struct{}
}

func (t *Tracker)Event(ctx context.Context, data string) error {
	select {
	case t.ch <- data:
		return nil
	case <-ctx.Done():
		return ctx.Err()
	}
}

func (t *Tracker) Run() {
	for data := range t.ch {
		time.Sleep(1 * time.Second)
		fmt.Println(data)
	}
	t.stop <- struct{}{}
}

func (t *Tracker) Shutdown(ctx context.Context) {
	close(t.ch)
	select {
	case <- t.stop:
	case <-ctx.Done():
	}
}