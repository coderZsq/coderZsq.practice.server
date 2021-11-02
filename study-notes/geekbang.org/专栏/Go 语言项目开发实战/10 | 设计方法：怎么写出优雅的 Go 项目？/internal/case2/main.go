package case2

type Post struct {
	Name    string
	Address string
}

type Service interface {
	ListPosts() ([]*Post, error)
}

func ListPosts(svc Service) ([]*Post, error) {
	return svc.ListPosts()
}