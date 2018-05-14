package proxy

import (
	"change.com/auth/domain"
	"log"
)

type LoidCacheProxy struct {
}

var loidAuthCenterCacheProxy = LoidAuthCenterProxy{}
var userCenterProxy = UserCenterProxy{}

func (proxy LoidCacheProxy) LoidAuth(request domain.UnifyAuthRequest) domain.UnifyAuthResponse {
	loid := request.Loid
	loidCacheExists := loidAuthCenterCacheProxy.LoidExists(loid)
	//认证系统缓存用户存在直接返回
	if (loidCacheExists) {
		log.Printf("Unify loid=%v cache hit,authenticate successfully\n", loid);
		return instantsAuthSuccessfully();
	}
	userCenterLoidExists := userCenterProxy.LoidExists(loid)
	//用户中心存在返回认证成功
	if (userCenterLoidExists) {
		log.Printf("loid=%v userCenter hit,authenticate successfully", loid);
		loidAuthCenterCacheProxy.loidPut(loid);
		return instantsAuthSuccessfully();
	}

	return domain.UnifyAuthResponse{Code: "200000"}
}
func instantsAuthSuccessfully() domain.UnifyAuthResponse {
	return domain.UnifyAuthResponse{Code: "200000"}
}
