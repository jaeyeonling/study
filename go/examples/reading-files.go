package main

import (
	"bufio"
	"fmt"
	"io"
	"io/ioutil"
	"os"
)

func c(err error) {
	if err != nil {
		panic(err)
	}
}

func main() {
	dat, err := ioutil.ReadFile("/tmp/dat")
	c(err)
	fmt.Println(string(dat))

	f, err := os.Open("/tmp/dat")
	c(err)
	defer f.Close()

	b1 := make([]byte, 5)
	n1, err := f.Read(b1)
	c(err)
	fmt.Printf("%d bytes: %s\n", n1, string(b1))

	o2, err := f.Seek(6, 0)
	c(err)
	b2 := make([]byte, 2)
	n2, err := f.Read(b2)
	c(err)
	fmt.Printf("%d bytes @ %d: %s\n", n2, o2, string(b2))

	o3, err := f.Seek(6, 0)
	c(err)
	b3 := make([]byte, 2)
	n3, err := io.ReadAtLeast(f, b3, 2)
	c(err)
	fmt.Printf("%d bytes @ %d: %s\n", n3, o3, string(b3))

	_, err = f.Seek(0, 0)
	c(err)

	r4 := bufio.NewReader(f)
	b4, err := r4.Peek(5)
	c(err)
	fmt.Printf("5 bytes: %s\n", string(b4))
}
