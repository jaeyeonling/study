package main

import (
  "fmt"
)

func main() {
  fmt.Println("Hello " + "World")
  fmt.Println(1 + 10)
  fmt.Println(1 + 23.45)
  fmt.Println(2.3 + 1.1)
  fmt.Println(10 / 3)
  // Error: fmt.Println("Hello " + 100)
  // fmt.Println("Hello " + string(100)) Hello b(ascii)
  fmt.Println(true && false)
  fmt.Println(!false)
}
