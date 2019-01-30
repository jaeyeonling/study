package main

import (
	"fmt"
	"io/ioutil"
	m "math"
	"net/http"
	"os"
	"strconv"
)

func main() {
	fmt.Println("Hello, playground")

	beyondHello()
}

func beyondHello() {
	var x int

	x = 3

	y := 4
	sum, prod := learnMultiple(x, y)

	fmt.Println("sum:", sum, "prod:", prod)

	learnTypes()
}

func learnMultiple(x, y int) (sum, prod int) {
	return x + y, x * y
}

func learnTypes() {
	str := "Learn Go!"

	s2 := `A "raw" string literal can include line breaks.`

	g := 'Σ'

	f := 3.14195
	c := 3 + 4i

	var u uint = 7
	var pi float32 = 22. / 7

	n := byte('\n')

	var a4 [4]int
	a5 := [...]int{3, 1, 5, 10, 100}

	s3 := []int{4, 5, 9}
	s4 := make([]int, 4)

	var d2 [][]float64

	bs := []byte("a slice")

	s := []int{1, 2, 3}
	s = append(s, 4, 5, 6)
	fmt.Println(s)

	s = append(s, []int{7, 8, 9}...)
	fmt.Println(s)

	p, q := learnMemory()
	fmt.Println(*p, *q)

	m := map[string]int{
		"three": 3,
		"four":  4,
	}
	m["one"] = 1

	_, _, _, _, _, _, _, _, _, _ = str, s2, g, f, u, pi, n, a5, s4, bs

	file, _ := os.Create("output.txt")
	fmt.Fprint(file, "This is how you write to a file, by the way")
	file.Close()

	fmt.Println(s, c, a4, s3, d2, m)

	learnFlowControl()
}

func learnNamedReturns(x, y int) (z int) {
	z = x * y
	return
}

func learnMemory() (p, q *int) {
	p = new(int)
	s := make([]int, 20)

	s[3] = 7
	r := -2

	return &s[3], &r
}

func expensiveComputation() float64 {
	return m.Exp(10)
}

func learnFlowControl() {
	if true {
		fmt.Println("told ya")
	}

	if false {

	} else {

	}

	x := 42.
	switch x {
	case 0:
	case 1:
	case 42:
	case 43:
	default:

	}

	for x := 0; x < 3; x++ {
		fmt.Println("interation", x)
	}

	for {
		break
		continue
	}

	for key, value := range map[string]int{
		"one":   1,
		"two":   2,
		"three": 3,
	} {
		fmt.Printf("key=%s, value=%d\n", key, value)
	}

	for _, name := range []string{
		"Bob",
		"Bill",
		"Joe",
	} {
		fmt.Printf("Hello, %s\n", name)
	}

	if y := expensiveComputation(); y > x {
		x = y
	}

	xBig := func() bool {
		return x > 10000
	}

	x = 99999
	fmt.Println("xBig:", xBig())

	x = 1.3e3
	fmt.Println("xBig:", xBig())

	fmt.Println("Add + double two numbers: ", func(a, b int) int {
		return (a + b) * 2
	}(10, 2))

	goto love
love:

	learnFunctionFactory()
	learnDefer()
	learnInterfaces()
}

func learnFunctionFactory() {
	fmt.Println(sentenceFactory("summer")("A beautiful", "day!"))

	d := sentenceFactory("summer")
	fmt.Println(d("A beautiful", "day!"))
	fmt.Println(d("A lazy", "afterrnoon!"))
}

func sentenceFactory(mystring string) func(before, after string) string {
	return func(before, after string) string {
		return fmt.Sprintf("%s %s %s", before, mystring, after)
	}
}

func learnDefer() (ok bool) {
	defer fmt.Println("deferred statements eecute in reverse (LIFO) order.")
	defer fmt.Println("\nThis line is being printed first because")

	return true
}

type Stringer interface {
	String() string
}

type pair struct {
	x, y int
}

func (p pair) String() string {
	return fmt.Sprintf("(%d, %d)", p.x, p.y)
}

func learnInterfaces() {
	p := pair{3, 4}
	fmt.Println(p.String())

	var i Stringer
	i = p
	fmt.Println(i.String())

	fmt.Println(p)
	fmt.Println(i)
}

func learnVariadicParams(myStrings ...interface{}) {
	for _, param := range myStrings {
		fmt.Println("param:", param)
	}

	fmt.Println("params:", fmt.Sprintln(myStrings...))

	learnErrorHandling()
}

func learnErrorHandling() {
	m := map[int]string{
		3: "three",
		4: "four",
	}

	if x, ok := m[1]; !ok {
		fmt.Println("no one there")
	} else {
		fmt.Print(x)
	}

	if _, err := strconv.Atoi("non-int"); err != nil {
		fmt.Println(err)
	}

	learnConcurrency()
}

func inc(i int, c chan int) {
	c <- i + 1
}

func learnConcurrency() {
	c := make(chan int)

	go inc(0, c)
	go inc(10, c)
	go inc(-805, c)

	fmt.Println(<-c, <-c, <-c)

	cs := make(chan string)
	ccs := make(chan chan string)

	go func() {
		c <- 84
	}()

	go func() {
		cs <- "wordy"
	}()

	select {
	case i := <-c:
		fmt.Printf("it's a %T", i)
	case <-cs:
		fmt.Println("it's a string")
	case <-ccs:
		fmt.Println("didn't happen")
	}

	learnWebProgramming()
}

func learnWebProgramming() {
	go func() {
		err := http.ListenAndServe(":8080", pair{})
		fmt.Println(err)
	}()

	requestServer()
}

func (p pair) ServeHTTP(rw http.ResponseWriter, r *http.Request) {
	rw.Write([]byte("You learned Go in Y minutes!"))
}

func requestServer() {
	resp, err := http.Get("http://localhost:8080")
	fmt.Println(err)
	defer resp.Body.Close()

	body, err := ioutil.ReadAll(resp.Body)
	fmt.Printf("\nWebserver said: `%s`", string(body))
}
