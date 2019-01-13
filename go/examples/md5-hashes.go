package main

import (
	"crypto/md5"
	"fmt"
)

func main() {
	s := "md5 this string"
	m := md5.New()
	m.Write([]byte(s))

	bs := m.Sum(nil)

	fmt.Println(s)
	fmt.Printf("%x\n", bs)
}
