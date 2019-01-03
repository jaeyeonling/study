package main

import (
	"fmt"
	"strconv"
	"time"
)

func speak(c chan<- string, sleepTimeSeconds int) {
	sleepDuration := time.Duration(sleepTimeSeconds) * time.Second
	time.Sleep(sleepDuration)

	c <- "Result " + strconv.Itoa(sleepTimeSeconds)
}

func show(c <-chan string, timeoutTimeSeconds int) {
	timeoutDuration := time.Duration(timeoutTimeSeconds) * time.Second

	var msg string
	select {
	case msg = <-c:
	case <-time.After(timeoutDuration):
		msg = "Timeout " + strconv.Itoa(timeoutTimeSeconds)
	}

	fmt.Println(msg)
}

func main() {
	c := make(chan string)

	go speak(c, 1)
	go show(c, 2)

	go speak(c, 3)
	go show(c, 2)

	fmt.Scanln()
}
