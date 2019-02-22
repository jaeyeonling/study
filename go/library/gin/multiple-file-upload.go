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
		form, _ := c.MultipartForm()
		files := form.File["file[]"]

		for _, file := range files {
			log.Println(file.Filename)
			// TODO: upload file
		}

		c.String(http.StatusOK, "%d files uploaded!", len(files))
	})

	r.Run(":8080")
}