package main

import (
	"bufio"
	"fmt"
	"io/ioutil"
	"os"
)

func c(err error) {
	if err != nil {
		panic(err)
	}
}

func main() {
	d1 := []byte("hello\ngo\n")
	err := ioutil.WriteFile("/tmp/dat1", d1, 0644)
	c(err)

	f, err := os.Create("/tmp/dat2")
	c(err)
	defer f.Close()

	d2 := []byte{115, 111, 109, 101, 10} // some
	n2, err := f.Write(d2)
	c(err)
	fmt.Printf("wrote %d bytes\n", n2)

	n3, err := f.WriteString("writes\n")
	c(err)
	fmt.Printf("wrote %d bytes\n", n3)

	err = f.Sync()
	c(err)

	w := bufio.NewWriter(f)
	n4, err := w.WriteString("buffered\n")
	fmt.Printf("wrote %d bytes\n", n4)

	w.Flush()
}
