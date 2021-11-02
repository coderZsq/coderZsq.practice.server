package main

import "time"

type Context interface {
	// Deadline return the time when work done on behalf of this context
	// should be canceled. Deadline returns ok==false when no deadline is
	// set.
	Deadline() (deadline time.Time, ok bool)
	// Done returns a channel that's closed when work done on behalf of this
	// context should be canceled.
	Done() <-chan struct{}
	// Err returns a non-nil error value after Done is closed.
	Err() error
	// Value returns the value associated with this context for key.
	Value(key interface{}) interface{}
}

func WithValue(parent Context, key, val interface{}) Context {
	if parent == nil {
		 panic("cannot create context from nil parent")
	}
	if key == nil {
		 panic("nil key")
	}
	//if !reflectlite.Type(key).Comparable() {
	//	panic("key is not comparable")
	//}
	return &valueCtx{parent, key, val}
}

// A valueCtx carries a key-value pair. It implements Value for that key and
// delegates all other calls to the embedded Context.
type valueCtx struct {
	Context
	key, val interface{}
}

func (c *valueCtx) Value(key interface{}) interface{} {
	if c.key == key {
		return c.val
	}
	return c.Context.Value(key)
}
