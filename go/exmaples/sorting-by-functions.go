package main

import (
	"fmt"
	"sort"
)

type byLength []string

func (s byLength) Len() int {
	return len(s)
}

func (s byLength) Swap(i int, j int) {
	temp := s[i]
	s[i] = s[j]
	s[j] = temp
}

func (s byLength) Less(i int, j int) bool {
	return len(s[i]) < len(s[j])
}

func main() {
	fruits := []string{"peach", "banana", "kiwi"}

	fmt.Println("Before:", fruits)
	sort.Sort(byLength(fruits))
	fmt.Println("After:", fruits)
}
