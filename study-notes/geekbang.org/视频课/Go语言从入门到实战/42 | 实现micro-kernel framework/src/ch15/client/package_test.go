package client

import (
	"go/src/ch15/series"
	"testing"
)

func TestPackage(t *testing.T)  {
	t.Log(series.GetFibonacciSeries(5))
	t.Log(series.Square(5))
}