package proxy

import (
	"hash/crc32"
	"io"
	"log"
	"change.com/auth/redis"
	"fmt"
	"encoding/json"
)

const (
	LOID          = "auth:loid:%v"
	LOID_MAX_HASH = 256
)

type LoidAuthCenterProxy struct {
}

type LoidCache struct {
	Loid string `json:"loid"`
}

func (proxy LoidAuthCenterProxy) LoidExists(loid string) bool {
	ieee := crc32.NewIEEE()
	io.WriteString(ieee, loid)
	redisKey := ieee.Sum32() % LOID_MAX_HASH
	result, e := redis.HExists(fmt.Sprintf(LOID, redisKey), loid)
	if e != nil {
		return result;
	}
	log.Printf("getCache loid=%v,key=%v,result=%v", loid, redisKey, result);
	return result
}
func (proxy LoidAuthCenterProxy) loidPut(loid string) {
	ieee := crc32.NewIEEE()
	io.WriteString(ieee, loid)
	redisKey := ieee.Sum32() % LOID_MAX_HASH
	var cache = LoidCache{Loid: loid}
	value, _ := json.Marshal(cache)
	redis.HSet(fmt.Sprintf(LOID, redisKey), loid, string(value))
}
