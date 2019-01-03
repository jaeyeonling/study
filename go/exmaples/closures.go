package main

import (
  "fmt"
)

func intSequence() func() int {
  i := 0
  return func() int {
    i++
    return i
  }
}

func main() {
  nextInt := intSequence()
  for i := 0; i < 10; i++ {
    fmt.Println(nextInt())
  }

  newNextInt := intSequence()
  for i := 0; i < 10; i++ {
    fmt.Println(newNextInt())
  }
}
