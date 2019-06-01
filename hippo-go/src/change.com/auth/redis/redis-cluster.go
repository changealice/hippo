package redis

import (
	"fmt"
	"github.com/astaxie/beego/logs"
	"github.com/go-redis/redis"
	"os"
	"time"
)

var (
	client *redis.ClusterClient
)

func init() {
	client = redis.NewClusterClient(&redis.ClusterOptions{
		Addrs:              []string{"192.168.0.179:36379"},
		PoolSize:           500,
		PoolTimeout:        300 * time.Second,
		IdleTimeout:        300 * time.Second,
		DialTimeout:        10 * time.Second,
		ReadTimeout:        1 * time.Second,
		WriteTimeout:       1 * time.Second,
		IdleCheckFrequency: 300 * time.Second,
	})
	err := client.Ping().Err()
	if err != nil {
		logs.Error("Redis cluster wrong")
	} else {
		logs.Info("Redis cluster OK")
	}

	r, err := client.ClusterNodes().Result()

	if err != nil || r == "" {
		logs.Info("Redis cluster wrong r=%v,err=%v", r, err)
		os.Exit(-1)
	}

	logs.Info("Redis cluster node r=%v,err=%v", r, err)
}

func Get(key string) (string, error) {
	data, err := client.Get(key).Result()
	if err != nil {
		return data, fmt.Errorf("error get key %s: %v", key, err)
	}
	return data, err
}

func HExists(key string, field string) (bool, error) {
	data, err := client.HExists(key, field).Result()
	if err != nil {
		return data, fmt.Errorf("error get key %s: %v", key, err)
	}
	return data, err
}

func Exists(key string) (int64, error) {
	data, err := client.Exists(key).Result()
	if err != nil {
		return data, fmt.Errorf("error get key %s: %v", key, err)
	}
	return data, err
}

func HSet(key string, field string, value string) (bool, error) {
	data, err := client.HSet(key, field, value).Result()
	if err != nil {
		return data, fmt.Errorf("error get key %s: %v", key, err)
	}
	return data, err
}

func Set(key string, value string, ttl time.Duration) (string, error) {
	data, err := client.Set(key, value, ttl).Result()
	if err != nil {
		return data, fmt.Errorf("error get key %s: %v", key, err)
	}
	return data, err
}
