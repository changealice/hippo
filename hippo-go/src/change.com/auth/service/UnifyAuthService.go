package service

import "change.com/auth/domain"

/**
光口号认证
 */
type UnifyAuthService interface {
	LoidAuth(request domain.UnifyAuthRequest) domain.UnifyAuthResponse
}
