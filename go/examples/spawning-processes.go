package main

import (
	"fmt"
	"io/ioutil"
	"os/exec"
)

func c(err error) {
	if err != nil {
		panic(err)
	}
}

func main() {
	dateCmd := exec.Command("date")
	dateOut, err := dateCmd.Output()
	c(err)

	fmt.Println("> date")
	fmt.Println(string(dateOut))

	grepCmd := exec.Command("grep", "hello")
	grepIn, err := grepCmd.StdinPipe()
	c(err)
	grepOut, err := grepCmd.StdoutPipe()
	c(err)

	c(grepCmd.Start())

	_, err = grepIn.Write([]byte("hello grep\ngoodbye grep"))
	c(err)
	c(grepIn.Close())

	grepBytes, err := ioutil.ReadAll(grepOut)
	c(err)
	c(grepCmd.Wait())

	fmt.Println("> grep hello")
	fmt.Println(string(grepBytes))

	lsCmd := exec.Command("bash", "-c", "ls -a -l -h")
	lsOut, err := lsCmd.Output()
	c(err)

	fmt.Println("> ls -a -l -h")
	fmt.Println(string(lsOut))
}
