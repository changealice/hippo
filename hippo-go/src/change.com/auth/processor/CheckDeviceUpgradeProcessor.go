package processor

import "change.com/auth/domain"

type CheckDeviceUpgradeProcessor struct {
}

func (c *CheckDeviceUpgradeProcessor) Authenticate(request domain.UnifyAuthRequest) domain.UnifyAuthResponse {
	return domain.UnifyAuthResponse{Code: "200000"}

}
