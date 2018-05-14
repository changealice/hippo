package proxy

import (
	"time"
	"net/http"
	"fmt"
	"io/ioutil"
	"log"
	"encoding/json"
)

type UserCenterProxy struct {
}

type UserInfo struct {
	LoId string;
}

type UserCenterResponse struct {
	Msg  string;
	Code string;
	Data UserInfo;
	Ok   bool;
}

var tr = &http.Transport{
	MaxIdleConns:       200,
	IdleConnTimeout:    300 * time.Second,
	DisableCompression: true,
}

func (proxy UserCenterProxy) LoidExists(loid string) bool {

	client := &http.Client{Transport: tr}
	resp, err := client.Get(fmt.Sprintf("http://192.168.0.179:30015/data/api/auth/findCustomerByLoid?loid=%v", loid))
	if err != nil {
		log.Printf("用户中心系统：loid=%v,ex={}", loid, err);
		return false
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	var usercenterResponse UserCenterResponse
	json.Unmarshal(body, &usercenterResponse)
	return usercenterResponse.Ok && usercenterResponse.Data.LoId != ""
}
