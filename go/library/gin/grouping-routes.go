package main

import "github.com/gin-gonic/gin"

func main() {
	r := gin.Default()

	v1 := r.Group("/v1")
	{
		// path: /v1/hello
		v1.GET("/hello", hello)
	}

	v2 := r.Group("/v2")
	{
		// path: /v2/hello
		v2.GET("/hello", hello)
	}

	r.Run(":8080")
}

func hello(c *gin.Context) { }