package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func main() {
	r := gin.Default()

	s := &http.Server{
		Addr: ":8080",
		Handler: r,
		MaxHeaderBytes: 1 << 20,
	}

	s.ListenAndServe()
}