package biz

import "example.com/eng/grpcli"

type Tran interface {
	Rollback()
	Commit() error
}

// Order
type Order struct {
	Item string
}

// OrderRepo
type OrderRepo interface {
	SaveOrder(*Order) error
	Begin() Tran
}

// NewOrderUsecase
func NewOrderUsecase(accountgRPC grpcli.Account, repo OrderRepo) *OrderUsecase {
	return &OrderUsecase{repo: repo}
}

// OrderUsecase
type OrderUsecase struct {
	repo OrderRepo
	accountgRPC grpcli.Account
}

// Buy
func (uc *OrderUsecase)Buy(o *Order) error {
	tr := uc.repo.Begin()
	err := uc.repo.SaveOrder(o)
	if err != nil {
		tr.Rollback()
	}
	return tr.Commit()
}