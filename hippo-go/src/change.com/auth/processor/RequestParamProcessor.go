package processor

import (
	"change.com/auth/domain"
	"context"
	"github.com/astaxie/beego/logs"
)

type RequestParamProcessor struct {
}

func (c *RequestParamProcessor) Authenticate(request *domain.UnifyAuthRequest, ctx context.Context) domain.UnifyAuthResponse {
	ip := ctx.Value("ip").(string)
	logs.Info("ips=%v", ip)
	request.Ip_address = ip
	return domain.UnifyAuthResponse{Code: "200000"}
}
