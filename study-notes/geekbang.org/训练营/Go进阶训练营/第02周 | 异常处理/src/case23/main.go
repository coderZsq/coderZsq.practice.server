package main

import (
	"errors"
	"os"
	"os/exec"
)

var err = errors.New("not found")

type QueryError struct {
	Query string
	Err   error
}

func (e *QueryError) Unwrap() error {
	return e.Err
}

func init() {
	// Similar to:
	// if err == ErrNotFound {...}
	if errors.Is(err, exec.ErrNotFound) {
		// something wasn't found
	}
}

func init() {
	// Similar to:
	// if e, ok := err.(*QueryError); ok {...}
	var e *QueryError
	// Note *QueryError is the type of the error.
	if errors.As(err, &e) {
		// err is a *QueryError, and e is set to the error's
	}
}

func init() {
	if errors.Is(err, os.ErrPermission) {
		// err, or some error that it wraps, is a permission problem
	}
}