package main

import (
	"time"
)

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

//func IsAdminUser(ctx context.Context) bool  {
//	x := token.GetToken(ctx)
//	userObject := auth.AuthenticateToken(x)
//	return userObject.IsAdmin() || userObject.IsRoot()
//}
