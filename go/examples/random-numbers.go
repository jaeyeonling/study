package main

import (
	"fmt"
	"math/rand"
	"time"
)

var p = fmt.Println

func main() {
	p(rand.Intn(100), rand.Intn(100))
	p(rand.Float64())
	p((rand.Float64()*5)+5, ",", (rand.Float64()*5)+5)

	r1 := rand.New(rand.NewSource(time.Now().UnixNano()))
	p(r1.Intn(100), ",", r1.Intn(100))

	r2 := rand.New(rand.NewSource(42))
	p(r2.Intn(100), ",", r2.Intn(100))

	r3 := rand.New(rand.NewSource(42))
	p(r3.Intn(100), ",", r3.Intn(100))
}
