package main

import (
	"fmt"
	"time"
)

func worker(id int, jobs <-chan int, results chan<- int) {
	for job := range jobs {
		fmt.Println("Worker", id, "started job", job)
		time.Sleep(time.Second)
		fmt.Println("Worker", id, "finished job", job)

		results <- job * 2
	}
}

func main() {
	jobs := make(chan int, 100)
	results := make(chan int, 100)

	for i := 1; i <= 3; i++ {
		go worker(i, jobs, results)
	}

	for i := 1; i <= 5; i++ {
		jobs <- i
	}
	close(jobs)

	for i := 1; i <= 5; i++ {
		<-results
	}
}
