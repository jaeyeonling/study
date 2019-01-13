package main

import (
	"fmt"
	"os"
)

func main() {
	os.Setenv("FOO", "1")
	fmt.Println("FOO:", os.Getenv("FOO"))
	fmt.Println("BAR:", os.Getenv("BAR"))

	fmt.Println()
	for _, env := range os.Environ() {
		fmt.Println(env)
	}
}
