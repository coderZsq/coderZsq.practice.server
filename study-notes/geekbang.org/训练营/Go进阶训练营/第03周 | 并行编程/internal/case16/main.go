package main

type Resource string

func Poller(in, out chan *Resource)  {
	for r := range in {
		// poll the URL

		// send the processed Resource to out
		out <- r
	}
}
