package main

import "fmt"

type Person struct {
	Name string
	Age int
}

func (p Person) Greet() {
	fmt.Printf("Hi! My name is %s", p.Name)
}
