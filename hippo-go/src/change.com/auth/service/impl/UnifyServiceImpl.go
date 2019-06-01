package impl

import (
	"change.com/auth/domain"
	"change.com/auth/processor"
	"change.com/auth/proxy"
	"context"
	"github.com/astaxie/beego/logs"
)

type UnifyAuthServiceImpl struct {
}

var loidAuthServiceProxy = &proxy.LoidCacheProxy{}

/**
 **UnifyAuthServiceImpl 实现光号口认证
 */
func (h UnifyAuthServiceImpl) LoidAuth(request domain.UnifyAuthRequest, ctx context.Context) domain.UnifyAuthResponse {
	logs.Info("Invoking with request=%v\n", request)

	var response domain.UnifyAuthResponse
	response = validate(request)

	if response.Code != "200000" {
		return response
	}

	//强制升级,黑名单逻辑。
	//不在黑名单、不需要升级都返回true
	response = preHandle(request, ctx)

	//加入黑名单
	postHandle(request, response)

	//如果不成功直接返回
	if &response != nil {
		copyProperties(request, response)
		if response.Code != "200000" {
			return response
		}
	}
	//设备认证
	response = loidAuthServiceProxy.LoidAuth(request)
	if response.Code == "200000" {
		/**返回调度策略与时长
		 */
		afterCompletion(request, response)
	}
	return response
}
func afterCompletion(request domain.UnifyAuthRequest, response domain.UnifyAuthResponse) {

}
func copyProperties(request domain.UnifyAuthRequest, response domain.UnifyAuthResponse) {

}

var preProcessors = []processor.CommonProcessor{
	&processor.RequestParamProcessor{},
	&processor.CheckBlackListByLoidProcessor{},
	&processor.CheckIpRateProcessor{},
	&processor.CheckDeviceUpgradeProcessor{},
}

func postHandle(request domain.UnifyAuthRequest, response domain.UnifyAuthResponse) {
}

func preHandle(request domain.UnifyAuthRequest, ctx context.Context) domain.UnifyAuthResponse {

	for index := 0; index < len(preProcessors); index++ {
		authenticate := preProcessors[index].Authenticate(&request, ctx)
		if &authenticate != nil && authenticate.Code != "200000" {
			return authenticate
		}
	}
	return domain.UnifyAuthResponse{Code: "200000"}
}

func validate(request domain.UnifyAuthRequest) domain.UnifyAuthResponse {
	if request.Loid == "" {
		return domain.UnifyAuthResponse{Code: "200002", Message: "Miss parameter: loid"}
	}
	if request.Sn == "" {
		return domain.UnifyAuthResponse{Code: "200002", Message: "Miss parameter: sn"}
	}
	return domain.UnifyAuthResponse{Code: "200000"}
}
