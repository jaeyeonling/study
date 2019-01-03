package main

import (
	"fmt"
	"math/rand"
	"sync"
	"sync/atomic"
	"time"
)

func main() {
	state := make(map[int]int)
	mutex := &sync.Mutex{}

	var readOps uint64
	var writeOps uint64

	for i := 0; i < 100; i++ {
		go func() {
			total := 0
			for {
				key := rand.Intn(5)
				mutex.Lock()
				total += state[key]
				mutex.Unlock()
				atomic.AddUint64(&readOps, 1)
			}
		}()
	}

	for i := 0; i < 10; i++ {
		go func() {
			for {
				key := rand.Intn(5)
				val := rand.Intn(100)
				mutex.Lock()
				state[key] = val
				mutex.Unlock()
				atomic.AddUint64(&writeOps, 1)
			}
		}()
	}

	time.Sleep(time.Second)

	for {
		readOpsFinal := atomic.LoadUint64(&readOps)
		fmt.Println("ReadOps:", readOpsFinal)

		writeOpsFinal := atomic.LoadUint64(&writeOps)
		fmt.Println("WriteOps:", writeOpsFinal)

		mutex.Lock()
		fmt.Println("State:", state)
		mutex.Unlock()
		time.Sleep(time.Second)
	}
}
