package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func main() {
	r := gin.Default()

	r.GET("/hello", func(c *gin.Context) {
		// get query
		// shortcut for c.Request.URL.Query().Get("lastname")
		lastname := c.Query("lastname")

		// get query or default value
		firstname := c.DefaultQuery("firstname", "Guest")

		c.String(http.StatusOK, "Hello %s %s", firstname, lastname)
	})

	r.Run(":8080")
}