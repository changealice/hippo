package proxy

import (
	"change.com/auth/domain"
	"github.com/astaxie/beego/logs"
)

type LoidCacheProxy struct {
}

var loidAuthCenterCacheProxy = LoidAuthCenterProxy{}
var userCenterProxy = UserCenterProxy{}

func (proxy LoidCacheProxy) LoidAuth(request domain.UnifyAuthRequest) domain.UnifyAuthResponse {
	loid := request.Loid
	loidCacheExists, loidHash := loidAuthCenterCacheProxy.LoidExists(loid)
	//认证系统缓存用户存在直接返回
	if loidCacheExists {
		logs.Info("Unify loid=%v cache hit,authenticate successfully\n", loid)
		return instantsAuthSuccessfully()
	}
	userCenterLoidExists := userCenterProxy.LoidExists(loid)
	//用户中心存在返回认证成功
	if userCenterLoidExists {
		logs.Info("loid=%v userCenter hit,authenticate successfully", loid)
		loidAuthCenterCacheProxy.loidPut(loid, loidHash)
		return instantsAuthSuccessfully()
	}

	return domain.UnifyAuthResponse{Code: "200000"}
}
func instantsAuthSuccessfully() domain.UnifyAuthResponse {
	return domain.UnifyAuthResponse{Code: "200000"}
}
