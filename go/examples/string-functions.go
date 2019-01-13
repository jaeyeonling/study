package main

import (
	"fmt"
	s "strings"
)

var p = fmt.Println
var w = "apple"

func main() {
	p(s.Contains(w, "app"))
	p(s.Contains(w, "ba"))

	p(s.Count(w, "p"))
	p(s.Count(w, "w"))

	p(s.HasPrefix(w, "app"))
	p(s.HasPrefix(w, "ba"))

	p(s.HasSuffix(w, "na"))
	p(s.HasSuffix(w, "app"))

	p(s.Index(w, "pl"))
	p(s.Index(w, "ba"))
	p(s.Index(w, "p"))

	p(s.Join([]string{"a", "pp", "l", "e"}, ""))
	p(s.Join([]string{"Hello", "World"}, "_"))

	p(s.Repeat(w, 5))

	p(s.Replace(w, "p", "P", -1))
	p(s.Replace(w, "p", "P", 1))
	p(s.Replace(w, "p", "P", 0))

	p(s.Split("a-p-p-l-e", "-"))
	p(s.Join(s.Split(w, ""), "-"))

	p(s.ToUpper(w))
	p(s.ToLower("HELLo, WorLd"))

	p(w[1])
}
