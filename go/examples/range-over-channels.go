package main

import "fmt"

func main() {
	queue := make(chan string, 2)

	queue <- "One"
	queue <- "Two"
	close(queue)

	for element := range queue {
		fmt.Println(element)
	}
}
