package main

/**
loid认证基础入口
*/
import (
	"change.com/auth/domain"
	"change.com/auth/realip"
	_ "change.com/auth/script"
	"change.com/auth/service/impl"
	"context"
	"encoding/json"
	"github.com/astaxie/beego/logs"
	"io/ioutil"
	"net/http"
)

const (
	AllowMethod    = "POST"
	ListenAndServe = ":19090"
	LoidAuth       = "/loidAuth"
	RID            = "rid"
)

func init() {
	logs.SetLogger(
		logs.AdapterFile,
		`{"filename":"/usr/local/applogs/auth-go/auth-go.log","level":7,"daily":true,"maxdays":7,"maxsize":500000000}`)
	logs.Async()
	logs.Async(2e4)
}

func main() {
	//异步启动apollo
	//go agollo.Start()
	http.HandleFunc(LoidAuth, service)
	address := ListenAndServe
	err := http.ListenAndServe(address, nil)
	logs.Info("Starting server on address %s", address)
	if err != nil {
		logs.Error("ListenAndServe: ", err)
		panic(err)
	}

}

func service(w http.ResponseWriter, r *http.Request) {
	m := r.Method
	if m == AllowMethod {
		loidAuth(w, r)
		return
	}
	http.NotFound(w, r)
	return
}

var unifyAuthService = &impl.UnifyAuthServiceImpl{}

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

	//处理ip
	ip := realip.FromRequest(r)
	ctx := context.WithValue(context.Background(), "ip", ip)

	rp := unifyAuthService.LoidAuth(request, ctx)
	re, err := json.Marshal(rp)
	if err != nil {
		http.Error(w, err.Error(), 500)
		return
	}
	w.Write(re)
}
