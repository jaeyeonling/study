package main

import (
	"fmt"
	"net/http"
)

func main() {
	http.HandleFunc("/", func(wr http.ResponseWriter, r *http.Request) {
		fmt.Fprint(wr, "Hello World!")
	})

	fs := http.FileServer(http.Dir("static/"))
	http.Handle("/static/", http.StripPrefix("/static/", fs))

	http.ListenAndServe(":80", nil)
}
