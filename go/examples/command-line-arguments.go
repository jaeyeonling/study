package main

import (
	"fmt"
	"os"
)

func main() {
	argsWithProg := os.Args
	fmt.Println(argsWithProg)

	argsWithoutProg := os.Args[1:]
	fmt.Println(argsWithoutProg)

	if len(os.Args) > 3 {
		args := os.Args[3]
		fmt.Println(args)
	}
}
