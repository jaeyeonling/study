package main

import (
	"bytes"
	"fmt"
	"regexp"
)

var p = fmt.Println
var pattern = "p([a-z]+)ch"

func main() {
	match, _ := regexp.MatchString(pattern, "peach")
	p(match)

	r, _ := regexp.Compile(pattern)
	p(r.MatchString("peach"))
	p(r.FindString("peach punch"))
	p(r.FindStringIndex("peach punch"))
	p(r.FindStringSubmatch("peach punch"))
	p(r.FindStringSubmatchIndex("peach punch"))
	p(r.FindAllString("peach punch pinch", -1))
	p()
	p(r.FindAllStringSubmatchIndex("peach punch pinch", -1))
	p(r.FindAllString("peach punch pinch", 2))
	p()
	p(r.Match([]byte("peach")))

	r = regexp.MustCompile(pattern)
	fmt.Println(r)

	fmt.Println(r.ReplaceAllString("a peach", "<fruit>"))

	in := []byte("a peach")
	out := r.ReplaceAllFunc(in, bytes.ToUpper)

	fmt.Println(string(out))
}
