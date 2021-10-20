package main

// ListDirectory returns the contents of dir
func ListDirectory(dir string) ([]string, error) {
	return nil, nil
}

// ListDirectory returns a channel over which
// directory entries will be published. When th list
// of entries is exhausted, the channel will be closed.
func ListDirectory2(dir string) chan string {
	return nil
}

func ListDirectory3(dir string, fn func(string))  {

}