package main

import (
	"net/http"
	"text/template"
)

type TodoPageData struct {
	PageTitle string
	Todos     []Todo
}

type Todo struct {
	Title string
	Done  bool
}

func main() {
	data := TodoPageData{
		PageTitle: "TODO List",
		Todos: []Todo{
			{
				Title: "Title 1",
				Done:  false,
			},
			{
				Title: "Title 2",
				Done:  true,
			},
			{
				Title: "Title 3",
				Done:  true,
			},
			{
				Title: "Title 4",
				Done:  false,
			},
			{
				Title: "Title 5",
				Done:  true,
			},
		},
	}

	template, err := template.ParseFiles("layout.html")
	if err != nil {
		panic(err)
	}
	/* or
	template := template.Must(template.ParseFiles("layout.html"))
	*/

	http.HandleFunc("/", func(rw http.ResponseWriter, r *http.Request) {
		template.Execute(rw, data)
	})

	http.ListenAndServe(":80", nil)
}
