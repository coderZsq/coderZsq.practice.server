package main

import (
	"encoding/json"
	"fmt"
	"go/types"
	"io"
	"log"
)

// WriteAll writes the contents of buf to the supplied writer
func WriteAll(w io.Writer, buf []byte) {
	w.Write(buf)
}

func WriteAll2(w io.Writer, buf []byte) error {
	_, err := w.Write(buf)
	if err != nil {
		log.Println("unable to write:", err) // annotated error goes to log file
		return err                           // unannotated error returned to caller
	}
	return nil
}

func WriteConfig(w io.Writer, conf *types.Config) error {
	buf, err := json.Marshal(conf)
	if err != nil {
		log.Printf("could not marshal config: %v", err)
		return err
	}
	if err := WriteAll2(w, buf); err != nil {
		log.Println("could not write config: %v", err)
		return err
	}
	return nil
}

func WriteConfig2(w io.Writer, conf *types.Config) error {
	buf, err := json.Marshal(conf)
	if err != nil {
		log.Printf("could not marshal config: %v", err)
		// oops, forgot to return
	}
	if err := WriteAll2(w, buf); err != nil {
		log.Println("could not write config: %v", err)
		return err
	}
	return nil
}

func main() {
	var f io.Writer
	var conf types.Config
	err := WriteConfig(f, &conf)
	fmt.Println(err)
}
