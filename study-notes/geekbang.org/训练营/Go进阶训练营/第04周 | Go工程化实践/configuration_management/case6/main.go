package main

import "fmt"

type Foo struct {
	verbosity int
}

func (f *Foo) Option(verbosity option) option {
	return verbosity(f)
}

type option func(f *Foo) option

// Verbosity sets Foo's verbosity level to v.
func Verbosity(v int) option {
	return func(f *Foo) option {
		prev := f.verbosity
		f.verbosity = v
		return Verbosity(prev)
	}
}

func DoSomethingVerbosely(foo *Foo, verbosity int) {
	// Could combine the next lines,
	// with some loss of readability.
	prev := foo.Option(Verbosity(verbosity))
	defer foo.Option(prev)
	// ... do some stuff with foo under high verbosity.
	fmt.Println(foo.verbosity)
}

func main() {
	foo := &Foo{ verbosity: 0}
	DoSomethingVerbosely(foo, 1)
	fmt.Println(foo.verbosity)
}
