package main

import (
	"fmt"
	"time"
)

var p = fmt.Println

func main() {
	now := time.Now()
	p(now.Format(time.RFC3339))

	if t, err := time.Parse(time.RFC3339, "2012-11-01T22:08:41+00:00"); err != nil {
		panic(err)
	} else {
		p(t)
	}

	p(now.Format("3:04PM"))
	p(now.Format("Mon Jan _2 15:04:05 2006"))
	p(now.Format("2006-01-02T15:04:05.999999-07:00"))

	if t, err := time.Parse("3 04 PM", "8 41 PM"); err != nil {
		panic(err)
	} else {
		p(t)
	}

	fmt.Printf("%d-%02d-%0dT%02d:%02d:%02d-00:00\n", now.Year(), now.Month(), now.Day(), now.Hour(), now.Minute(), now.Second())

	if t, err := time.Parse("Mon Jan _2 15:04:05 2006", "8:41PM"); err != nil {
		panic(err)
	} else {
		p(t)
	}
}
