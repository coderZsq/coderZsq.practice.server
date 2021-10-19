package main

import (
	"fmt"
	"github.com/pkg/errors"
	"os"
)

func parseArgs1(args []string) error {
	if len(args) < 3 {
		return errors.Errorf("not enough arguments, expected at least.")
	}
	// ...
	return nil
}

func parseArgs2() error {
	err := parseArgs1([]string{})
	if err != nil {
		return err
	}
	return nil
}

func parseArgs3() error {
	path := ""
	_, err := os.Open(path)
	if err != nil {
		 return errors.Wrapf(err, "failed to open %q", path)
	}
	return nil
}

func parseArgs4() error {
	//err := app.Run()
	//if err != nil {
	//	fmt.Printf("FATAL: ^+v\n", err)
	//	os.Exit(1)
	//}
}