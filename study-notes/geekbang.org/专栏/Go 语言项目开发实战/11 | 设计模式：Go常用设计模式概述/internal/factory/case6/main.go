package main

type Person struct {
	name string
	age int
}

func NewPersonFactory(age int) func(name string) Person {
	return func(name string) Person {
		return Person{
			name: name,
			age: age,
		}
	}
}

func main() {
	newBaby := NewPersonFactory(1)
	baby := newBaby("john")

	newTeenager := NewPersonFactory(16)
	teen := newTeenager("jill")

	_, _ = baby, teen
}