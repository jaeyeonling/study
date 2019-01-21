package main

import (
	"fmt"
	"log"
	"net/http"
	"time"
)

/* Basic Sample
func NewMiddleware() Middleware {
	return func(next http.HandlerFunc) http.HandlerFunc {
		return func(rw http.ResponseWriter, r *http.Request) {

			// ... do middleware things

			// call the next middleware/handler in chain
			next(rw, r)
		}
	}
}
*/

type Middleware func(http.HandlerFunc) http.HandlerFunc

func Logging() Middleware {
	return func(f http.HandlerFunc) http.HandlerFunc {
		return func(rw http.ResponseWriter, r *http.Request) {
			start := time.Now()
			defer func() {
				log.Println(r.URL.Path, time.Since(start))
			}()

			f(rw, r)
		}
	}
}

func Method(method string) Middleware {
	return func(f http.HandlerFunc) http.HandlerFunc {
		return func(rw http.ResponseWriter, r *http.Request) {
			if r.Method != method {
				http.Error(rw, http.StatusText(http.StatusMethodNotAllowed), http.StatusMethodNotAllowed)
				return
			}

			f(rw, r)
		}
	}
}

func Chain(f http.HandlerFunc, middlewares ...Middleware) http.HandlerFunc {
	for _, middleware := range middlewares {
		f = middleware(f)
	}

	return f
}

func Hello(rw http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(rw, "Hello World")
}

func main() {
	http.HandleFunc("/", Chain(Hello, Method("POST"), Logging()))
	http.ListenAndServe(":80", nil)
}
