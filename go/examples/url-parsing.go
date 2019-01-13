package main

import (
	"fmt"
	"net"
	"net/url"
)

var p = fmt.Println
var s = "postgres://user:pass@host.com:5432/path?k=v#f"

func main() {
	fmt.Println(s)

	u, err := url.Parse(s)
	if err != nil {
		panic(err)
	}

	p(u.Scheme)
	p(u.User)
	p(u.User.Username())

	password, _ := u.User.Password()
	p(password)

	p(u.Host)

	host, port, _ := net.SplitHostPort(u.Host)
	p(host)
	p(port)

	p(u.Path)
	p(u.Fragment)

	p(u.RawQuery)

	m, err := url.ParseQuery(u.RawQuery)
	if err != nil {
		panic(err)
	}

	p(m)
	p(m["k"])
	p(m["k"][0])
}
