package main

type temporary interface {
	Temporary() bool
}

// IsTemporary returns true if err is temporary
func IsTemporary(err error) bool {
	te, ok := err.(temporary)
	return ok && te.Temporary()
}
