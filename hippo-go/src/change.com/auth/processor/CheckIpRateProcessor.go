package processor

import (
	"change.com/auth/domain"
	"context"
	"github.com/astaxie/beego/logs"
)

type CheckIpRateProcessor struct {
}

func (c *CheckIpRateProcessor) Authenticate(request *domain.UnifyAuthRequest, ctx context.Context) domain.UnifyAuthResponse {
	ip := request.Ip_address
	loid := request.Loid
	logs.Info("ip=%v,loid=%v", ip, loid)
	return domain.UnifyAuthResponse{Code: "200000"}

}
