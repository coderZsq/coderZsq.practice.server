package main

import "context"

// A message processes parameter and returns the result on responseChan.
// ctx is places in a struct, but this is ok to do.
type message struct {
	responseChan chan<- int
	parameter    string
	ctx          context.Context
}
