package main

import (
	"fmt"
	"strings"
)

func Index(strs []string, value string) int {

	for i, str := range strs {
		if str == value {
			return i
		}
	}

	return -1
}

func Include(strs []string, value string) bool {
	for _, str := range strs {
		if str == value {
			return true
		}
	}

	return false
}

func Any(strs []string, callback func(string) bool) bool {
	for _, str := range strs {
		if callback(str) {
			return true
		}
	}

	return false
}

func All(strs []string, callback func(string) bool) bool {
	for _, str := range strs {
		if !callback(str) {
			return false
		}
	}

	return true
}

func Filter(strs []string, callback func(string) bool) []string {
	results := make([]string, 0)
	for _, str := range strs {
		if callback(str) {
			results = append(results, str)
		}
	}

	return results
}

func Map(strs []string, callback func(string) string) []string {
	results := make([]string, len(strs))
	for i, str := range strs {
		results[i] = callback(str)
	}

	return results
}

func main() {
	strs := []string{"peach", "apple", "pear", "plum", "kiwi"}

	fmt.Println("Index:", Index(strs, "pear"))
	fmt.Println("Include:", Include(strs, "grape"))
	fmt.Println("Include:", Include(strs, "apple"))
	fmt.Println("Any:", Any(strs, func(str string) bool {
		return strings.HasPrefix(str, "p")
	}))
	fmt.Println("All:", All(strs, func(str string) bool {
		return strings.HasPrefix(str, "p")
	}))
	fmt.Println("Filter:", Filter(strs, func(str string) bool {
		return strings.Contains(str, "e")
	}))
	fmt.Println("Map:", Map(strs, strings.ToUpper))
}
