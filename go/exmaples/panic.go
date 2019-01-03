package main

func main() {
	panic("Problem")

	_, err := os.Create("/tmp/file")
	if err != nil {
		panic(err)
	}
}
