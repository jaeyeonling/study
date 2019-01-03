package main

import (
	"fmt"
	"time"
)

func main() {
	c1 := make(chan string)
	c2 := make(chan string)

	go func() {
		time.Sleep(time.Second)
		c1 <- "One"
	}()

	go func() {
		time.Sleep(2 * time.Second)
		c2 <- "Two"
	}()

	for i := 0; i < 2; i++ {
		var msg string
		select {
		case msg = <-c1:
		case msg = <-c2:
		}

		fmt.Println("Received: ", msg)
	}
}
