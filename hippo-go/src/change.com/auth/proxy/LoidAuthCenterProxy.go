package proxy

import (
	"change.com/auth/redis"
	"encoding/json"
	"fmt"
	"github.com/astaxie/beego/logs"
	"hash/crc32"
	"io"
)

const (
	LOID        = "auth:loid:%v"
	LoidMaxHash = 256
)

type LoidAuthCenterProxy struct {
}

type LoidCache struct {
	Loid string `json:"loid"`
}

func (proxy LoidAuthCenterProxy) LoidExists(loid string) (bool, string) {
	ieee := crc32.NewIEEE()
	io.WriteString(ieee, loid)
	loidMod := ieee.Sum32() % LoidMaxHash
	hash := fmt.Sprintf(LOID, loidMod)
	result, e := redis.HExists(hash, loid)
	if e != nil {
		return result, hash
	}
	logs.Info("getCache loid=%v,key=%v,result=%v", loid, loidMod, result)
	return result, hash
}
func (proxy LoidAuthCenterProxy) loidPut(loid string, hash string) {
	var cache = LoidCache{Loid: loid}
	value, _ := json.Marshal(cache)
	redis.HSet(hash, loid, string(value))
}
