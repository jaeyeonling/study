package main

import (
	"encoding/json"
	"fmt"
	"os"
)

var m = json.Marshal
var p = fmt.Println

type response1 struct {
	Page   int
	Fruits []string
}

type response2 struct {
	Page   int      `json:"page"`
	Fruits []string `json:"fruits"`
}

type response3 struct {
	page   int
	fruits []string
}

type response4 struct {
	page   int      `json:"page"`
	fruits []string `json:"fruits"`
}

func main() {
	bolB, _ := m(true)
	p(string(bolB))

	intB, _ := m(1)
	p(string(intB))

	fltB, _ := m(2.345)
	p(string(fltB))

	strB, _ := m("gopher")
	p(string(strB))

	slcD := []string{"apple", "peach", "pear"}
	slcB, _ := m(slcD)
	p(string(slcB))

	mapD := map[string]int{"apple": 5, "lettuce": 7}
	mapB, _ := m(mapD)
	p(string(mapB))

	res1D := &response1{
		Page:   1,
		Fruits: []string{"apple", "peach", "pear"},
	}
	res1B, _ := m(res1D)
	p(string(res1B))

	res2D := &response2{
		Page:   1,
		Fruits: []string{"apple", "peach", "pear"},
	}
	res2B, _ := m(res2D)
	p(string(res2B))

	res3D := &response3{
		page:   1,
		fruits: []string{"apple", "peach", "pear"},
	}
	res3B, _ := m(res3D)
	p(string(res3B))

	res4D := &response4{
		page:   1,
		fruits: []string{"apple", "peach", "pear"},
	}
	res4B, _ := m(res4D)
	p(string(res4B))

	bytes := []byte(`{"num": 6.13, "strs": ["a", "b"]}`)
	var dat map[string]interface{}
	if err := json.Unmarshal(bytes, &dat); err != nil {
		panic(err)
	}
	p(dat)

	num := dat["num"].(float64)
	p(num)

	strs := dat["strs"].([]interface{})
	str1 := strs[0].(string)
	p(str1)

	str := `{"page": 1, "fruits": ["apple", "peach"]}`
	res := response2{}
	if err := json.Unmarshal([]byte(str), &res); err != nil {
		panic(err)
	}
	p(res)
	p(res.Fruits[0])

	enc := json.NewEncoder(os.Stdout)
	d := map[string]int{"apple": 5, "lettuce": 7}
	if err := enc.Encode(d); err != nil {
		panic(err)
	}
}
