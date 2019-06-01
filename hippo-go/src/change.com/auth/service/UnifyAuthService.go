package service

import (
	"change.com/auth/domain"
	"context"
)

/**
光口号认证
*/
type UnifyAuthService interface {
	LoidAuth(request domain.UnifyAuthRequest, ctx context.Context) domain.UnifyAuthResponse
}
