package main

import (
  "fmt"
)

func main() {
  s := make([]string, 3)
  fmt.Println("empty: ", s)
  
  s[0] = "a"
  s[1] = "b"
  s[2] = "c"
  fmt.Println("set: ", s)
  fmt.Println("get: ", s[2])
  fmt.Println("len: ", len(s))

  s = append(s, "d")
  s = append(s, "e", "f")
  fmt.Println("append: ", s)

  c := make([]string, len(s))
  fmt.Println(c)

  copy(c, s)
  fmt.Println("copy: ", c)

  l := s[2:5]
  fmt.Println("slice1: ", l)

  l = s[:5]
  fmt.Println("slice2: ", l)

  l = s[2:]
  fmt.Println("slice3: ", l)

  t := []string{"g", "h", "i"} 
  fmt.Println("declare: ", t) 

  twoData := make([][]int, 3)
  for i := 0; i < len(twoData); i++ {
    innerLen := i + 1
    twoData[i] = make([]int, innerLen)
    for j := 0; j < innerLen; j++ {
      twoData[i][j] = i + j
    }
  }

  fmt.Println("twoData: ", twoData)
}
