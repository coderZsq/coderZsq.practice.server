package main

import (
	"github.com/pkg/errors"
	"io"
	"log"
)

func Write(w io.Writer, buf []byte) error {
	_, err := w.Write(buf)
	if err != nil {
		// annotated error goes to log file
		log.Println("unable to write:", err)

		// unannotated error returned to caller
		return err
	}
	return nil
}

func Write2(w io.Writer, buf []byte) error {
	_, err := w.Write(buf)
	return errors.Wrap(err, "write failed")
}