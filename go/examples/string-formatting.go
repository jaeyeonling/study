package main

import (
	"fmt"
	"os"
)

type point struct {
	x, y int
}

var p = fmt.Printf

func main() {
	po := point{1, 2}

	p("%v\n", po)
	p("%+v\n", po)
	p("%#v\n", po)
	p("%T\n", po)

	p("%t\n", true)
	p("%d\n", 123)
	p("%b\n", 14)
	p("%c\n", 33)
	p("%x\n", 456)
	p("%f\n", 78.9)

	p("%e\n", 123400000.0)
	p("%E\n", 123400000.0)

	p("%s\n", "\"string\"")
	p("%q\n", "\"string\"")
	p("%x\n", "hex this")

	p("%p\n", &po)

	p("|%6d|%6d|\n", 12, 345)
	p("|%6.2f|%6.2f|\n", 1.2, 3.45)
	p("|%-6.2f|%-6.2f|\n", 1.2, 3.45)
	p("|%6s|%6s|\n", "foo", "b")
	p("|%-6s|%-6s|\n", "foo", "b")

	s := fmt.Sprintf("a %s", "string")
	fmt.Println(s)

	fmt.Fprintf(os.Stderr, "an %s\n", "error")
}
