package main

import (
	"testing"
	"time"
)

func BenchmarkWithNoBuffer(b *testing.B) {
	benchmarkWithBuffer(b, 0)
}

func BenchmarkWithBufferSizeOf1(b *testing.B) {
	benchmarkWithBuffer(b, 1)
}

func BenchmarkWithBufferSizeEqualsToNumberOfWorker(b *testing.B) {
	benchmarkWithBuffer(b, 5)
}

func BenchmarkWithBufferSizeExceedsNumberOfWorker(b *testing.B) {
	benchmarkWithBuffer(b, 25)
}

func benchmarkWithBuffer(b *testing.B, i int) {
	ch := make(chan int, i)
	var total []int
	go func() {
		for i := 0; i < 1000000; i++ {
			ch <- i
		}
	}()
	for i := 0; i < 5; i++ {
		go func() {
			for {
				total = append(total, <-ch)
			}
		}()
	}
	time.Sleep(1 * time.Second)
}
