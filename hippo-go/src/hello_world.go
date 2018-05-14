package main

import (
	"fmt"
	. "fmt"
	"mymath"
	"errors"
	"math/rand"
	_ "github.com/ziutek/mymysql/godrv"
)

var isActive bool

const (
	charset = "utf-8"
	sys     = "mac"
	pi      = 3.14
)

func main() {
	fmt.Printf("Hello, world or 你好，世界 or καλημ ́ρα κóσμ or こんにちはせかい\n")
	fmt.Printf("Hello, world.  Sqrt(2) = %v\n", mymath.Sqrt(2))
	fmt.Printf("Hello, world.  Sqrt Hello = %v\n", mymath.Hello)
	fmt.Println("My favorite number is",rand.Intn(10))
	const i = 10000

	var available bool // 一般声明
	fmt.Printf("bool= %v\n", available)
	fmt.Printf("isActive= %v\n", isActive)

	a := 3
	b := true
	fmt.Printf("a = %v\n", a)
	fmt.Printf("b = %v\n", b)

	fmt.Printf("a+i = %v\n", a+i)

	var hello_world = "hello world"
	fmt.Printf("hello_world = %v\n", hello_world)

	var err = errors.New("deal with error")

	fmt.Printf("err = %v\n", err)

	fmt.Printf("const charset=%v\n", charset)
	fmt.Printf("const sys=%v\n", sys)
	fmt.Printf("const pi=%v\n", pi)

	var array [10] int
	array[1] = 1
	array[9] = 1
	var sArray = [10]string{"123", "456", "678"}

	fmt.Printf("array int =%v\n", array)
	fmt.Printf("sArray int =%v\n", sArray)

	var numbers map[string]int
	numbers = make(map[string]int)
	numbers["one"] = 1
	numbers["two"] = 2
	numbers["three"] = 3

	fmt.Printf("number map =%v\n", numbers)

	if i > 10 {
		fmt.Println("i greater than 10")
	} else {
		fmt.Println("i less than 10")
	}

	if i == 3 {
		fmt.Println("The integer is equal to 3")
	} else if i < 3 {
		fmt.Println("The integer is less than 3")
	} else {
		fmt.Println("The integer is greater than 3")
	}
	sum := 0
	for index := 0; index < 10; index++ {
		sum += index
	}
	fmt.Println("sum is equal to ", sum)

	// 计算获取值x,然后根据x返回的大小，判断是否大于10。
	if x := computedValue(); x > 10 {
		fmt.Println("x is greater than 10")
	} else {
		fmt.Println("x is less than 10")
	}
	fmt.Println(abs(-10))


	x := 3

	fmt.Println("x = ", x)  // 应该输出 "x = 3"

	x1 := add1(&x)  // 调用 add1(&x) 传x的地址

	fmt.Println("x+1 = ", x1) // 应该输出 "x+1 = 4"
	fmt.Println("x = ", x)    // 应该输出 "x = 4"

	for i := 0; i < 5; i++ {
		defer fmt.Printf("%d ", i)
	}
	fmt.Println()
	Println(".println")


}
func computedValue() int {
	return 9
}

func abs(a int) int {
	if a > 0 {
		return a;
	}
	return -a
}

//简单的一个函数，实现了参数+1的操作
func add1(a *int) int { // 请注意，
	*a = *a+1 // 修改了a的值
	return *a // 返回新值
}

func readWrite() bool  {
	return true;
}
