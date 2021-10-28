package case1

type singleton struct {
}

var ins *singleton = &singleton{}

func GetInsOr() *singleton {
	return ins
}