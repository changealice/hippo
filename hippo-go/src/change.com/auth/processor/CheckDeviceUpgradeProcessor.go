package processor

import (
	"change.com/auth/domain"
	"context"
)

type CheckDeviceUpgradeProcessor struct {
}

func (c *CheckDeviceUpgradeProcessor) Authenticate(request *domain.UnifyAuthRequest, ctx context.Context) domain.UnifyAuthResponse {
	return domain.UnifyAuthResponse{Code: "200000"}

}
