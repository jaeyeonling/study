package main

import "github.com/gin-gonic/gin"

func main() {
	r := gin.Default()
	r.GET("/get", get)
	r.POST("/post", post)
	r.PUT("/put", put)
	r.DELETE("/delete", delete)
	r.PATCH("/patch", patch)
	r.HEAD("/head", head)
	r.OPTIONS("/options", options)

	r.Run(":8080")
}

func get(c *gin.Context) { }
func post(c *gin.Context) { }
func put(c *gin.Context) { }
func delete(c *gin.Context) { }
func patch(c *gin.Context) { }
func head(c *gin.Context) { }
func options(c *gin.Context) { }