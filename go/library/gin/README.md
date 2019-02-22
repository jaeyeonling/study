### Installation

1. go get -u github.com/gin-gonic/gin
2. import "github.com/gin-gonic/gin"



### Quick Start

```go
package main

import "github.com/gin-gonic/gin"

func main() {
	r := gin.Default()
	r.GET("/ping", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"message": "pong",
		})
	})

    // default listen and serve on :8080
	r.Run() 
}
```



### API Examples

```go
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
```



### Parameters in Path

```go
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
```



### Querystring Parameters

```go
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
````


### Multipart/Urlencoded Form

```go
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
```


### Single File Upload

```go
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
```



### Multiple File Upload

```go
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
```



### Grouping Routes

```go
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
```



### Middleware

```go
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
```



### JSON Binding

```go
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
```



### Custom Middleware

```go
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
```



### Custom HTTP Configuration

```go
package main

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func main() {
	r := gin.Default()

	s := &http.Server{
		Addr: ":8080",
		Handler: r,
		MaxHeaderBytes: 1 << 20,
	}

	s.ListenAndServe()
}
```
