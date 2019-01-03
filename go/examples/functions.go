package main

import (
  "fmt"
)

func plus(a int, b int) int {
  return a + b
}

func minus(a int, b int) int {
  return a - b
}

func vals() (int, int) {
  return 3, 7
}

func sum(nums ...int) int {
  total := 0
  for _, num := range nums {
    total += num
  }
  return total
}

func main() {
  fmt.Println("1 + 2 = ", plus(1, 2))
  fmt.Println("5 - 3 = ", minus(5, 3))

  a, b := vals()
  fmt.Println(a, b)
  fmt.Println(plus(vals()))

  fmt.Println(sum(2,4,5,6,8,8,7,5,4))
  fmt.Println(sum(vals()))
  
  nums := []int{1,2,3,4,5,6,7}
  fmt.Println(sum(nums...))
}
