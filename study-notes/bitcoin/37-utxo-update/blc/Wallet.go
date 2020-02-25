package blc

import (
	"bytes"
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"log"

	"golang.org/x/crypto/ripemd160"
)

// 钱包管理相关文件
// 校验和长度
const addressCheckSumLen = 4

// Wallet 钱包基本结构
type Wallet struct {
	// 1. 私钥
	PrivateKey ecdsa.PrivateKey
	// 2. 公钥
	PublicKey []byte
}

// NewWallet 创建一个钱包
func NewWallet() *Wallet {
	// 公钥-私钥赋值
	privateKey, publicKey := newKeyPair()
	return &Wallet{privateKey, publicKey}
}

// 通过钱包生成公钥-私钥对
func newKeyPair() (ecdsa.PrivateKey, []byte) {
	// 1. 获取一个椭圆
	curve := elliptic.P256()
	// 2. 通过椭圆相关算法生成私钥
	priv, err := ecdsa.GenerateKey(curve, rand.Reader)
	if nil != err {
		log.Panicf("ecdsa generate private key failed! %v\n", err)
	}
	// 3. 通过私钥生成公钥
	pubKey := append(priv.PublicKey.X.Bytes(), priv.PublicKey.Y.Bytes()...)
	return *priv, pubKey
}

// 生成地址

// Ripemd160Hash 实现双哈希
func Ripemd160Hash(pubKey []byte) []byte {
	// 1. sha256
	hash256 := sha256.New()
	hash256.Write(pubKey)
	hash := hash256.Sum(nil)
	// 2. ripemd160
	rmd160 := ripemd160.New()
	rmd160.Write(hash)
	return rmd160.Sum(nil)
}

// CheckSum 生成校验和
func CheckSum(input []byte) []byte {
	firstHash := sha256.Sum256(input)
	secondHash := sha256.Sum256(firstHash[:])
	return secondHash[:addressCheckSumLen]
}

// GetAddress 通过钱包(公钥)获取地址
func (w *Wallet) GetAddress() []byte {
	// 1. 获取hash160
	ripemd160Hash := Ripemd160Hash(w.PublicKey)
	// 2. 获取校验和
	checkSumBytes := CheckSum(ripemd160Hash)
	// 3. 地址组成成员拼接
	addressBytes := append(ripemd160Hash, checkSumBytes...)
	b58Bytes := Base58Encode(addressBytes)
	return b58Bytes
}

// IsValidForAddress 判断地址有效性
func IsValidForAddress(addressBytes []byte) bool {
	// 1. 地址通过base58Decode进行解码(长度为24)
	pubkeyCheckSumBytes := Base58Decode(addressBytes)
	// 2. 拆分, 进行校验和校验
	checkSumBytes := pubkeyCheckSumBytes[len(pubkeyCheckSumBytes)-addressCheckSumLen:]
	//    传入ripemd160hash, 生成校验和
	ripemd160hash := pubkeyCheckSumBytes[:len(pubkeyCheckSumBytes)-addressCheckSumLen]
	// 3. 生成
	checkBytes := CheckSum(ripemd160hash)
	// 4. 比较
	if bytes.Compare(checkSumBytes, checkBytes) == 0 {
		return true
	}
	return false
}
