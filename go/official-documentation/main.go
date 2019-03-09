package main

import (
	"fmt"
	"log"
	"net/http"
)

func handler(rw http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(rw, "Hi there, I love %s!", r.URL.Path[1:])
}

func main() {
	http.HandleFunc("/", handler)
	log.Fatal(http.ListenAndServe(":8080", nil))

}
