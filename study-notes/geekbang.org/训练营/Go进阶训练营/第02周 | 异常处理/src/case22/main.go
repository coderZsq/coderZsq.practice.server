package main

import (
	"errors"
	"fmt"
	"os"
)

var err = errors.New("not found")

func init() {
	if err != nil {
		// something went wrong
	}
}

func init() {
	var ErrNotFound = errors.New("not found")

	if err == ErrNotFound {
		// something wasn't found
	}
}

type NotFoundError struct {
	Name string
}

func (e *NotFoundError) Error() string {
	return e.Name + ": not found"
}

func init() {
	if e, ok := err.(*NotFoundError); ok {
		// e.Name wasn't found
	} else {
		fmt.Printf("%+v", e)
	}
}

func errorf() error {
	var name string
	if err != nil {
		return fmt.Errorf("decompress %v: %v", name, err)
	}
	return nil
}

type QueryError struct {
	Query string
	Err   error
}

func (qe *QueryError)Error() string {
	return ""
}

func init() {
	if e, ok := err.(*QueryError); ok && e.Err == os.ErrPermission {
		// query failed because of permission problem
	}
}