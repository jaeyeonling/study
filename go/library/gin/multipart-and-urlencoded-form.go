package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func main() {
	r := gin.Default()

	//
	r.POST("/post", func(c *gin.Context) {
		message := c.PostForm("message")
		name := c.DefaultPostForm("name", "anonymous")

		c.JSON(http.StatusOK, gin.H{
			"message": message,
			"name": name,
		})
	})

	r.Run(":8080")
}