package main

import (
	"fmt"
	"net/http"

	"github.com/gorilla/mux"
)

func main() {
	r := mux.NewRouter()

	// URL Parameter
	r.HandleFunc("/books/{title}/page/{page}", func(rw http.ResponseWriter, r *http.Request) {
		vars := mux.Vars(r)
		title := vars["title"]
		page := vars["page"]

		fmt.Fprintf(rw, "request [book=%s, page=%s]", title, page)
	})

	// methods
	// GET POST PUT DELETE ...
	r.HandleFunc("/books", func(rw http.ResponseWriter, r *http.Request) {
		fmt.Fprintf(rw, "get request [book=%s]", mux.Vars(r)["title"])
	}).Methods("GET")

	// hostnames & subdomains
	r.HandleFunc("/host", func(rw http.ResponseWriter, r *http.Request) {
		fmt.Fprint(rw, "host www.jaeyeonling.com")
	}).Host("www.jaeyeonling.com")

	// schemes
	// TODO: this code not working (404 not found)
	r.HandleFunc("/secure", func(rw http.ResponseWriter, r *http.Request) {
		fmt.Fprint(rw, "https schemes secure")
	}).Schemes("https")
	r.HandleFunc("/insecure", func(rw http.ResponseWriter, r *http.Request) {
		fmt.Fprint(rw, "http schemes insecure")
	}).Schemes("http")

	// path prefixes & subrouters
	router := r.PathPrefix("/route").Subrouter()
	router.HandleFunc("/", func(rw http.ResponseWriter, r *http.Request) {
		fmt.Fprint(rw, "default routing")
	})
	router.HandleFunc("/hello", func(rw http.ResponseWriter, r *http.Request) {
		fmt.Fprint(rw, "hello routing")
	})

	http.ListenAndServe(":80", r)
}
