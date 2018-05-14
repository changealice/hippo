package main

import (
	"math"
	"fmt"
)
//定义一个接口
type Vertx interface {
	area() float64
}


//定义一个 矩形对象
type Rectangle struct {
	width, height float64
}

//顶一个园对象
type Circle struct {
	radius float64
}

//求矩形的面积
func (r Rectangle) area() float64 {
	return r.height * r.width
}

func (c Circle) area() float64 {
	return c.radius * c.radius * math.Pi
}

func main() {
	r1 := Rectangle{12, 2}
	r2 := Rectangle{9, 4}
	c1 := Circle{10}
	c2 := Circle{2}

	fmt.Println("Area of r1 is: ", r1.area())
	fmt.Println("Area of r2 is: ", r2.area())
	fmt.Println("Area of r3 is: ", c1.area())
	fmt.Println("Area of r4 is: ", c2.area())
}
