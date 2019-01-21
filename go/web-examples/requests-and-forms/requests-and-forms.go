package main

import (
	"fmt"
	"html/template"
	"net/http"
)

type ContactDetails struct {
	Email   string
	Subject string
	Message string
}

func main() {
	template := template.Must(template.ParseFiles("forms.html"))

	http.HandleFunc("/", func(rw http.ResponseWriter, r *http.Request) {
		if r.Method != http.MethodPost {
			template.Execute(rw, nil)
			return
		}

		details := ContactDetails{
			Email:   r.FormValue("email"),
			Subject: r.FormValue("subject"),
			Message: r.FormValue("message"),
		}

		fmt.Printf("%+v", details)

		template.Execute(rw, struct{ Success bool }{true})
	})

	http.ListenAndServe(":80", nil)
}
