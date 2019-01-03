package main

import "fmt"

func ping(pings chan<- string, message string) {
	pings <- message
}

func pong(pings <-chan string, pongs chan<- string) {
	pongs <- <-pings
}

func main() {
	pings := make(chan string)
	pongs := make(chan string)

	go ping(pings, "Ping!")
	go pong(pings, pongs)

	fmt.Println(<-pongs)
}
