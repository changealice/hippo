package processor

import "change.com/auth/domain"

type CheckIpRateProcessor struct {
}

func (c *CheckIpRateProcessor) Authenticate(request domain.UnifyAuthRequest) domain.UnifyAuthResponse {
	return domain.UnifyAuthResponse{Code: "200000"}

}
