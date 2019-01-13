package main

import (
	"fmt"
	"strconv"
)

var p = fmt.Println

func main() {
	if f, err := strconv.ParseFloat("1.234", 64); err != nil {
		panic(err)
	} else {
		p(f)
	}

	if i, err := strconv.ParseInt("123", 0, 64); err != nil {
		panic(err)
	} else {
		p(i)
	}

	if d, err := strconv.ParseInt("0x1c8", 0, 64); err != nil {
		panic(err)
	} else {
		p(d)
	}

	if u, err := strconv.ParseUint("789", 0, 64); err != nil {
		panic(err)
	} else {
		p(u)
	}

	if k, err := strconv.Atoi("135"); err != nil {
		panic(err)
	} else {
		p(k)
	}

	if k, err := strconv.Atoi("wat"); err != nil {
		panic(err)
	} else {
		p(k)
	}
}
