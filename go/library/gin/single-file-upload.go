package main

import (
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
)

func main() {
	r := gin.Default()

	//
	r.POST("/post", func(c *gin.Context) {
		file, _ := c.FormFile("file")

		log.Println(file.Filename)
		// TODO: upload file

		c.String(http.StatusOK, "%s uploaded!", file.Filename)
	})

	r.Run(":8080")
}