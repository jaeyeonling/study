package main

import (
  "fmt"
)

type person struct {
  name	string
  age	int
}

func main() {
  fmt.Println(person{})
  fmt.Println(person{"Jaeyeonling", 26, })
  fmt.Println(person{name: "Jaeyeonling", age: 26, })
  fmt.Println(person{name: "Jaeyeonling",})
  fmt.Println(&person{"Jaeyeonling", 26, })

  s := person{"Jaeyeonling", 26, }
  fmt.Println(s.age)

  sp := &s
  fmt.Println(sp.age)

  sp.age = 51
  fmt.Println(s.age)
}
