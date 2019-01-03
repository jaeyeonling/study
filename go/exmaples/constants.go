package main

import (
	"fmt"
)

const S string = "constant"

func main() {
	fmt.Println(S)

	const n = 5000000000

	const d = 3e20 / n

	fmt.Println(d)
	fmt.Println(int64(d))
}
