package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func main() {
	r := gin.Default()

	// match: /user/jaeyeon
	// not match: /user or /user/
	r.GET("/user/:name", func(c *gin.Context) {
		name := c.Param("name")
		c.String(http.StatusOK, "Hello %s", name)
	})

	// match: /user/jaeyeon/hello or /user/jaeyeon/
	// not match: /user/jaeyeon (if not other routers match /user/jaeyeon, it will rediect to /user/jaeyeon/)
	r.GET("/user/:name/*message", func(c *gin.Context) {
		name := c.Param("name")
		message := c.Param("message")

		c.String(http.StatusOK, "%s say %s", name, message)
	})

	r.Run(":8080")
}