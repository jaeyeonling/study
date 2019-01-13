package main

import (
	"fmt"
	"time"
)

var p = fmt.Println

func main() {
	now := time.Now()
	secs := now.Unix()
	nanos := now.UnixNano()

	p(now)

	millis := nanos / 100000
	p(secs)
	p(millis)
	p(nanos)

	p(time.Unix(secs, 0))
	p(time.Unix(0, nanos))
}
