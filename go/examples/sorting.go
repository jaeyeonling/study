package main

import (
	"fmt"
	"sort"
)

func main() {
	strings := []string{"c", "a", "b"}

	fmt.Println("Strings before:", strings)
	sort.Strings(strings)
	fmt.Println("Strings after:", strings)

	ints := []int{7, 2, 6}

	fmt.Println("Ints before:", ints)
	sort.Ints(ints)
	fmt.Println("Ints after:", ints)

	isSorted := sort.IntsAreSorted(ints)
	fmt.Println("Ints is sorted:", isSorted)
}
