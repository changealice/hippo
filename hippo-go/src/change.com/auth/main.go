package main

/**
loid认证基础入口
 */
import (
	"net/http"
	"log"
	"change.com/auth/domain"
	loidAuthImpl "change.com/auth/service/impl"
	"io/ioutil"
	"encoding/json"
)

const (
	ALLOW_METHOD = "POST"
	ListenAndServe = ":19090"
	LOID_AUTH = "/loidAuth"
)

func main() {
	http.HandleFunc(LOID_AUTH, service)
	address := ListenAndServe
	err := http.ListenAndServe(address, nil)
	log.Println("Starting server on address", address)
	if err != nil {
		log.Fatal("ListenAndServe: ", err)
		panic(err)
	}

}

func service(w http.ResponseWriter, r *http.Request) {
	m := r.Method
	if m == ALLOW_METHOD {
		loidAuth(w, r)
		return
	}
	http.NotFound(w, r)
	return
}

var impl = loidAuthImpl.UnifyServiceImpl{}

func loidAuth(w http.ResponseWriter, r *http.Request) {
	// Read body
	b, err := ioutil.ReadAll(r.Body)
	defer r.Body.Close()

	if err != nil {
		http.Error(w, err.Error(), 500)
		return
	}

	var request domain.UnifyAuthRequest
	err = json.Unmarshal(b, &request)
	if err != nil {
		http.Error(w, err.Error(), 500)
		return
	}
	w.Header().Set("Content-Type", "application/json;charset=UTF-8")

	rp := impl.LoidAuth(request)
	re, err := json.Marshal(rp)
	if err != nil {
		http.Error(w, err.Error(), 500)
		return
	}
	w.Write(re)
}
