package main

import (
  "fmt"
)

func sum(nums ...int) {
  fmt.Print(nums, " ")

  sum := 0
  for _, num := range nums {
    sum = sum + num
  }

  fmt.Println(sum)
}

func main() {
  sum(1,2)
  sum(1,2,3)

  arr := []int{1,2,3,4,5,6,7}
  sum(arr...)
}
