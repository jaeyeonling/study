package main

import (
	"encoding/base64"
	"fmt"
)

var p = fmt.Println

func main() {
	data := "abc123!@#$%^&*()"

	sEnc := base64.StdEncoding.EncodeToString([]byte(data))
	p(sEnc)

	if sDes, err := base64.StdEncoding.DecodeString(sEnc); err != nil {
		panic(err)
	} else {
		p(string(sDes))
	}

	uEnc := base64.URLEncoding.EncodeToString([]byte(data))
	p(uEnc)

	if uDec, err := base64.URLEncoding.DecodeString(uEnc); err != nil {
		panic(err)
	} else {
		p(string(uDec))
	}
}
