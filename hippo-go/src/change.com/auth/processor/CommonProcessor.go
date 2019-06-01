package processor

import (
	"change.com/auth/domain"
	"context"
)

/**
* 用户认证处理器
 */
type CommonProcessor interface {
	Authenticate(request *domain.UnifyAuthRequest, ctx context.Context) domain.UnifyAuthResponse
}
