package main

import "github.com/gin-gonic/gin"

func main() {
	// gin.Default() is with the Logger and Recovery middleware already attached
	r := gin.New()

	// ginDefaultWriter = os.Stdout
	r.Use(gin.Logger())

	// Per route middleware
	r.GET("/recovery()", gin.Recovery(), func(_ *gin.Context) {
		panic("Panic!!")
	})

	r.Run(":8080")
}