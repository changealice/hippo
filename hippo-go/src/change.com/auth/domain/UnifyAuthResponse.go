package domain

type AuthResult struct {
	Controller_ip string
	Delay_period  int64
	Token         string
	File_path     string
	Md5           string
}

/**
 认证返回
 */
type UnifyAuthResponse struct {
	Id                        string `json:"id,omitempty"`
	Code                      string `json:"code,omitempty"`
	Message                   string `json:"message,omitempty"`
	Connection_retry_interval int64  `json:"connection_retry_interval,omitempty"`
	result                    AuthResult
}
