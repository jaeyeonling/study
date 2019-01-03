package main

import (
	"fmt"
	"time"
)

func worker(done chan bool) {
	fmt.Print("Working... ")
	time.Sleep(time.Second)
	fmt.Print("Done!")

	done <- true
}

func main() {
	done := make(chan bool)
	go worker(done)

	<-done
}
