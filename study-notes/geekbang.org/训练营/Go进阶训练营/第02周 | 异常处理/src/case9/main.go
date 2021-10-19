package main

import "fmt"

type MyError struct {
	Msg  string
	File string
	Line int
}

func (e *MyError) Error() string {
	return fmt.Sprintf("%s:%d: %s", e.File, e.Line, e.Msg)
}

func test() error {
	return &MyError{"Something happened", "server.go", 42}
}

func main() {
	err := test()
	switch err := err.(type) {
	case nil:
	// call succeeded, nothing to do
	case *MyError:
		fmt.Println("error occurred on line:", err.Line)
	default:
		// unknown error
	}
}
