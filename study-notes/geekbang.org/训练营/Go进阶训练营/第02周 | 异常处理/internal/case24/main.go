package main

import (
	"errors"
	"fmt"
	"os"
)

var name string
var err = errors.New("not found")

func fmtErrorf() error {
	if err != nil {
		return fmt.Errorf("decompress %v: %v", name, err)
	}
	return nil
}

func fmtErrorf2() error {
	if err != nil {
		return fmt.Errorf("decompress %v: %w", name, err)
	}
	return nil
}

func fmtErrorf3() error {
	err := fmt.Errorf("access denied: %w", os.ErrPermission)
	// ...
	if errors.Is(err, os.ErrPermission) {

	}
	return nil
}

type wrapError struct {
	msg string
	err error
}

func (e *wrapError) Error() string {
	return e.msg
}

func (e *wrapError) Unwrap() error {
	return e.err
}
