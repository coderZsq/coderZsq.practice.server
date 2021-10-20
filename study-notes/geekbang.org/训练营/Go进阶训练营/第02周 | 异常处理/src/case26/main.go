package main

import (
	"errors"
	"fmt"
)

var ErrPermission = errors.New("permission denied")

// DoSomething returns an error wrapping ErrPermission if the user
// does not have permission to do something.
func DoSomething() error {
	if !userHasPermission() {
		// If we return ErrPermission directly, callers might come
		// to depend on the exact error value, writing code like this:
		//
		// if err := pkg.DoSomething(); err == pkg.ErrPermission {...}
		//
		// This will cause problems if we want to add additional
		// context to the error in the future. To avoid this, we
		// return an error wrapping the sentinel so that users must
		// always unwrap it:
		//
		// if err := pkg.DoSomething(); errors.Is(err, pkg.ErrPermission) {...}
		return fmt.Errorf("%w", ErrPermission)
	}
	// ...
	return nil
}

func userHasPermission() bool {
	return false
}

