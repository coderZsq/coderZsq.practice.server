package main

import "fmt"

// Positive returns true if the number is positive, false if it is negative.
// The second return value indicates if the result is valid, which in the case
// of n == 0, is not valid.
func Positive(n int) (bool, bool) {
	if n == 0 {
		return false, false
	}
	return n > -1, true
}

func Check(n int)  {
	pos, ok := Positive(n)
	if !ok {
		fmt.Println(n, "is neither")
		return
	}
	if pos {
		fmt.Println(n, "is positive")
	} else {
		fmt.Println(n, "is negative")
	}
}

func main() {
	Check(1)
	Check(0)
	Check(-1)
}
