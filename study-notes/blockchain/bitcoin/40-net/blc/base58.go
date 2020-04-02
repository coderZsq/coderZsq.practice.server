package blc

import (
	"bytes"
	"math/big"
)

// base58编码实现
// 1. 生成一个base58的编码基数表
var b58Alphabet = []byte("" +
	"123456789" +
	"abcdefghijkmnopqrstuvwxyz" +
	"ABCDEFGHJKLMNPQRSTUVWXYZ")

// Base58Encode 编码函数
func Base58Encode(input []byte) []byte {
	var result []byte // 结果
	// big.int
	// byte字节数组转换为big.int
	x := big.NewInt(0).SetBytes(input)
	base := big.NewInt(int64(len(b58Alphabet)))
	// 求余数和商
	// 判断条件, 除掉的最终结果是否为0
	zero := big.NewInt(0)
	// 设置余数, 代表base58基数表的索引位置
	mod := &big.Int{}
	for x.Cmp(zero) != 0 {
		x.DivMod(x, base, mod)
		// 得到的result是一个倒序的base58编码结果
		result = append(result, b58Alphabet[mod.Int64()])
	}
	// 返回result切片
	Reverse(result)
	// 添加一个前缀1, 代表是一个地址
	result = append([]byte{b58Alphabet[0]}, result...)
	return result
}

// Reverse 反转切片函数
func Reverse(data []byte) {
	for i, j := 0, len(data)-1; i < j; i, j = i+1, j-1 {
		data[i], data[j] = data[j], data[i]
	}
}

// Base58Decode 解码函数
// input: base58编码结果
func Base58Decode(input []byte) []byte {
	result := big.NewInt(0)
	// 前缀索引
	zeroBytes := 1
	// 1. 去掉前缀
	data := input[zeroBytes:]
	for _, b := range data {
		// 2. 查找input中指定数字/字符串基数表中出现的索引(mod)
		//var index int
		//for i, value := range b58Alphabet{
		//	if b == value {
		//		index = i
		//	}
		//}
		charIndex := bytes.IndexByte(b58Alphabet, b) // 内部函数, 返回字符在切片中第一次出现的索引
		// 3. 余数*58
		result.Mul(result, big.NewInt(58))
		// 4. 乘积结果+mod(索引)
		result.Add(result, big.NewInt(int64(charIndex)))
	}
	// 5. 转换为byte字节数组
	decoded := result.Bytes()
	return decoded
}
