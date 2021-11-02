package case2

import "testing"

type fakeService struct {
}

func NewFakeService() Service {
	return &fakeService{}
}

func (s *fakeService) ListPosts() ([]*Post, error) {
	posts := make([]*Post, 0)
	posts = append(posts, &Post{
		Name:    "colin",
		Address: "Shenzhen",
	})
	posts = append(posts, &Post{
		Name:    "alex",
		Address: "Beijing",
	})
	return posts, nil
}

func TestListPosts(t *testing.T) {
	fake := NewFakeService()
	if _, err := ListPosts(fake); err != nil {
		t.Fatal("list posts failed")
	}
}