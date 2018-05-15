package processor

import "change.com/auth/domain"

type RequestParamProcessor struct {
}

func (c *RequestParamProcessor)Authenticate(request domain.UnifyAuthRequest) domain.UnifyAuthResponse {
	return domain.UnifyAuthResponse{Code: "200000"}

}
