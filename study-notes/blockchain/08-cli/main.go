package main

import (
	"flag"
	"fmt"
)

// 命令行回顾
// 定义一个字符串变量
var species = flag.String("species", "go", "the usage of flag")

// 定义一个int字符
var num = flag.Int("ins", 1, "ins nums")

// $ go build -o block
// $ block -ins 3 -species gopher
func main() {
	// 解析, 在flags各种类型参数生效之前, 需要对参数进行解析
	flag.Parse()
	//var s = []int{1, 2, 3, 4, 5}
	//fmt.Println(s[2:4])
	// 打印参数
	fmt.Println("a string flag: ", *species)
	fmt.Println("ins num: ", *num)
}
