package main

import (
	"encoding/json"
	"fmt"
	"net/http"
)

type User struct {
	Firstname string `json:"firstname"`
	Lastname  string `json:"lastname"`
	Age       int    `json:"age"`
}

func main() {
	http.HandleFunc("/decode", func(rw http.ResponseWriter, r *http.Request) {
		var user User
		json.NewDecoder(r.Body).Decode(&user)

		fmt.Fprintf(rw, "%s %s is %d years old!", user.Firstname, user.Lastname, user.Age)
	})

	http.HandleFunc("/encode", func(rw http.ResponseWriter, r *http.Request) {
		json.NewEncoder(rw).Encode(User{
			Firstname: "Jaeyeon",
			Lastname:  "Kim",
			Age:       27,
		})
	})

	http.ListenAndServe(":80", nil)
}
