package processor

import (
	"change.com/auth/domain"
	"change.com/auth/redis"
	"context"
	"fmt"
	"github.com/astaxie/beego/logs"
	"time"
)

type CheckBlackListByLoidProcessor struct {
}

func (c *CheckBlackListByLoidProcessor) Authenticate(request *domain.UnifyAuthRequest, ctx context.Context) domain.UnifyAuthResponse {
	loid := request.Loid
	cacheKey := fmt.Sprintf("auth:loid:blacklist:%s", loid)
	result, err := redis.Exists(cacheKey)

	redis.Set(cacheKey, "1", time.Minute*100)
	if result == 1 && err == nil {
		logs.Info("CheckBlackListByLoidProcessor active,black Loid={}", loid)
		return domain.UnifyAuthResponse{Code: "200005"}
	}
	return domain.UnifyAuthResponse{Code: "200000"}
}
