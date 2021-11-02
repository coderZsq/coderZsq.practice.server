package main

import (
	"errors"
	"fmt"
	"sort"
	"strings"
)

// type IntSet struct {
//     data map[int]bool
// }

// func NewIntSet() IntSet {
//     return IntSet{make(map[int]bool)}
// }

// func (set *IntSet) Add(x int) {
//     set.data[x] = true
// }

// func (set *IntSet) Delete(x int) {
//     delete(set.data, x)
// }

// func (set *IntSet) Contains(x int) bool {
//     return set.data[x]
// }


type IntSet struct {
    data map[int]bool
    undo Undo
}

func NewIntSet() IntSet {
    return IntSet{data: make(map[int]bool)}
}

func (set *IntSet) Add(x int) {
    if !set.Contains(x) {
        set.data[x] = true
        set.undo.Add(func() { set.Delete(x) })
    } else {
        set.undo.Add(nil)
    }
}

func (set *IntSet) Delete(x int) {
    if set.Contains(x) {
        delete(set.data, x)
        set.undo.Add(func() { set.Add(x) })
    } else {
        set.undo.Add(nil)
    }
}

func (set *IntSet) Undo() error {
    return set.undo.Undo()
}

func (set *IntSet) Contains(x int) bool {
    return set.data[x]
}

func (set *IntSet) String() string { // Satisfies fmt.Stringer interface
    if len(set.data) == 0 {
        return "{}"
    }
    ints := make([]int, 0, len(set.data))
    for i := range set.data {
        ints = append(ints, i)
    }
    sort.Ints(ints)
    parts := make([]string, 0, len(ints))
    for _, i := range ints {
        parts = append(parts, fmt.Sprint(i))
    }
    return "{" + strings.Join(parts, ",") + "}"
}


type UndoableIntSet struct { // Poor style
    IntSet    // Embedding (delegation)
    functions []func()
}

func NewUndoableIntSet() UndoableIntSet {
    return UndoableIntSet{NewIntSet(), nil}
}

func (set *UndoableIntSet) Add(x int) { // Override
    if !set.Contains(x) {
        set.data[x] = true
        set.functions = append(set.functions, func() { set.Delete(x) })
    } else {
        set.functions = append(set.functions, nil)
    }
}

func (set *UndoableIntSet) Delete(x int) { // Override
    if set.Contains(x) {
        delete(set.data, x)
        set.functions = append(set.functions, func() { set.Add(x) })
    } else {
        set.functions = append(set.functions, nil)
    }
}

func (set *UndoableIntSet) Undo() error {
    if len(set.functions) == 0 {
        return errors.New("No functions to undo")
    }
    index := len(set.functions) - 1
    if function := set.functions[index]; function != nil {
        function()
        set.functions[index] = nil // Free closure for garbage collection
    }
    set.functions = set.functions[:index]
    return nil
}

type Undo []func()

func (undo *Undo) Add(function func()) {
    *undo = append(*undo, function)
}

func (undo *Undo) Undo() error {
    functions := *undo
    if len(functions) == 0 {
        return errors.New("No functions to undo")
    }
    index := len(functions) - 1
    if function := functions[index]; function != nil {
        function()
        functions[index] = nil // Free closure for garbage collection
    }
    *undo = functions[:index]
    return nil
}

func main() {
		// ints := NewIntSet()
		// for _, i := range []int{1, 3, 5, 7} {
		// 		ints.Add(i)
		// 		fmt.Println(ints)
		// }
		// for _, i := range []int{1, 2, 3, 4, 5, 6, 7} {
		// 		fmt.Print(i, ints.Contains(i), " ")
		// 		ints.Delete(i)
		// 		fmt.Println(ints)
		// }

		ints := NewUndoableIntSet()
		for _, i := range []int{1, 3, 5, 7} {
				ints.Add(i)
				fmt.Println(ints)
		}
		for _, i := range []int{1, 2, 3, 4, 5, 6, 7} {
				fmt.Println(i, ints.Contains(i), " ")
				ints.Delete(i)
				fmt.Println(ints)
		}
		fmt.Println()
		for {
				if err := ints.Undo(); err != nil {
						break
				}
				fmt.Println(ints)
		}
}