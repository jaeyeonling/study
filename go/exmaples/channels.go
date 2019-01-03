package main

import (
	"fmt"
	"strconv"
)

func main() {
	messages := make(chan string)

	i := 0
	go func() {
		for {
			messages <- strconv.Itoa(i)
			i = i + 1
		}
	}()

	go func() {
		for {
			message := <-messages
			fmt.Println(message)
		}
	}()

	fmt.Scanln()
}
