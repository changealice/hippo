package proxy

import (
	"encoding/json"
	"fmt"
	"github.com/astaxie/beego/logs"
	"io/ioutil"
	"net"
	"net/http"
	"time"
)

type UserCenterProxy struct {
}

type UserInfo struct {
	LoId string
}

type UserCenterResponse struct {
	Msg  string
	Code string
	Data UserInfo
	Ok   bool
}

var tr = &http.Transport{
	MaxIdleConns:        200,
	MaxIdleConnsPerHost: 200,
	IdleConnTimeout:     90 * time.Second,
	DisableCompression:  true,
	DialContext: (&net.Dialer{
		Timeout:   30 * time.Second,
		KeepAlive: 30 * time.Second,
	}).DialContext,
}

func (proxy UserCenterProxy) LoidExists(loid string) bool {

	client := &http.Client{Transport: tr}
	resp, err := client.Get(fmt.Sprintf("http://192.168.0.179:30015/data/api/auth/findCustomerByLoid?loid=%v", loid))
	if err != nil {
		logs.Error("用户中心系统：loid=%v,ex={}", loid, err)
		return false
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	var usercenterResponse UserCenterResponse
	json.Unmarshal(body, &usercenterResponse)
	return usercenterResponse.Ok && usercenterResponse.Data.LoId != ""
}
