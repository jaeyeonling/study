package main

import (
	"fmt"
	"os"
)

func createFile(path string) *os.File {
	fmt.Println("Createing")

	file, err := os.Create(path)
	if err != nil {
		panic(err)
	}

	return file
}

func closeFile(file *os.File) {
	fmt.Println("Closing")

	file.Close()
}

func writeFile(file *os.File) {
	fmt.Println("Writing")

	fmt.Fprintln(file, "data")
}

func main() {
	f := createFile("/tmp/defer.txt")
	defer closeFile(f)
	writeFile(f)
}
