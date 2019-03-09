package main

import (
	"fmt"
	"io/ioutil"
)

type Page struct {
	Title string
	Body  []byte
}

func main() {
	title := "TestPage"
	p1 := &Page{
		Title: title,
		Body:  []byte("This is a sample Page."),
	}
	p1.save()

	p2, err := loadPage(title)
	if err != nil {
		panic(err)
	}

	fmt.Println(string(p2.Body))
}

func (p *Page) save() error {
	filename := p.Title + ".txt"
	return ioutil.WriteFile(filename, p.Body, 0600)
}

func loadPage(title string) (*Page, error) {
	filename := title + ".txt"
	body, err := ioutil.ReadFile(filename)
	if err != nil {
		return nil, err
	}

	return &Page{
		Title: title,
		Body:  body,
	}, nil
}
