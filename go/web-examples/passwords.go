package main

import (
	"fmt"

	"golang.org/x/crypto/bcrypt"
)

var cost = 14

func HashPassword(password string) (string, error) {
	bytes, err := bcrypt.GenerateFromPassword([]byte(password), cost)
	return string(bytes), err
}

func CheckPasswordHash(hashedPassword, password string) bool {
	return bcrypt.CompareHashAndPassword([]byte(hashedPassword), []byte(password)) == nil
}

func main() {
	password := "password"

	hashedPassword, err := HashPassword(password)
	if err != nil {
		panic(err)
	}

	fmt.Println("Password: ", password)
	fmt.Println("HashedPassword: ", hashedPassword)

	match := CheckPasswordHash(hashedPassword, password)
	fmt.Println("Match: ", match)

	notMatch := CheckPasswordHash(hashedPassword, "HelloWorld")
	fmt.Println("NotMatch: ", notMatch)
}
