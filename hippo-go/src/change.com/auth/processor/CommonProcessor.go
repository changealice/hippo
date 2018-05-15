package processor

import "change.com/auth/domain"

/**
* 用户认证处理器
*/
type CommonProcessor interface {
	Authenticate(request domain.UnifyAuthRequest) domain.UnifyAuthResponse
}
