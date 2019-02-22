package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

type User struct {
	Name string `json:"name"`
	Age int `json:"age"`
}

func main() {
	r := gin.Default()

	// request body: {"name": "Jaeyeon", "age": 30}
	r.POST("/json", func(c *gin.Context) {
		var user User

		// c.ShouldBind() is use depending on the content-type header
		if err := c.ShouldBind(&user); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}
		// OR
		if err := c.ShouldBindJSON(&user); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
			return
		}

		c.JSON(http.StatusOK, gin.H{"status": "OK"})
	})

	r.Run(":8080")
}