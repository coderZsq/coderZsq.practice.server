package main

import (
	"errors"
)

//func Is(err, target error) bool {
//	if target == nil {
//		return err == target
//	}
//
//	isComparable := reflectlite.TypeOf(target).Comparable()
//	for {
//		if isComparable && err == target {
//			return true
//		}
//		if x, ok := err.(interface{ Is(error) bool }); ok && x.Is(target) {
//			return true
//		}
//		// TODO: consider supporting target.Is(err). This would allow
//		// user-definable predicates, but also may allow for coping with sloppy
//		// APIs, thereby making it easier to get away with them.
//		if err = Unwrap(err); err == nil {
//			return false
//		}
//	}
//}

var err = errors.New("not found")

type Error struct {
	Path string
	User string
}

func (e Error) Error() string {
	return ""
}

func (e *Error) Is(target error) bool {
	t, ok := target.(*Error)
	if !ok {
		return false
	}
	return (e.Path == t.Path || t.Path == "") &&
		(e.User == t.User || t.User == "")
}

func main() {
	if errors.Is(err, &Error{User: "someuser"}) {
		// err's User filed is "someuser".
	}
}