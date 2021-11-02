package main

import (
	"math"
	"math/rand"
)

type stat struct {
}

func (s *stat) Value() (int, int) {
	return 0, 0
}

type serBreaker struct {
	stat
	k       float64
	request int
	r       rand.Rand
}

func (b *serBreaker) Allow() error {
	success, total := b.stat.Value()
	k := b.k * float64(success)

	if total < b.request || float64(total) < k {
		return nil
	}
	dr := math.Max(0, (float64(total)-k)/float64(total+1))
	rr := b.r.Float64()

	if dr <= rr {
		return nil
	}
	return ecode.ServiceUnavailable
}
