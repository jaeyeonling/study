package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"log"
	"time"
)

func main() {
	r := gin.Default()

	r.Use(Logging())

	r.GET("/", func(c *gin.Context) {
		tempVar := c.MustGet("tempVar").(string)
		fmt.Println(tempVar)
	})

	r.Run(":8080")
}

func Logging() gin.HandlerFunc {
	return func(c *gin.Context) {
		t := time.Now()

		c.Next()

		// latency
		log.Println(time.Since(t), c.Writer.Status())
	}
}