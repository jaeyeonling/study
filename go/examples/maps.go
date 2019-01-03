package main

import (
  "fmt"
)

func main() {
  m := make(map[string]int)

  m["k1"] = 7
  m["k2"] = 13
  fmt.Println("map: ", m)
  fmt.Println("len: ", len(m))

  v1 := m["k1"]
  fmt.Println("v1: ", v1)

  delete(m, "k2")
  fmt.Println("map: ", m)

  aa, prs := m["k2"]
  fmt.Println("prs: ", prs)

  m = map[string]int{"foo": 1, "bar": 2,}
  fmt.Println("map: ", m)
}
