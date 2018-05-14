package domain

//升级信息
type LastInfos struct {
	Last_upgrade_status  int
	Last_upgrade_version string
	Last_error_code      int
	Last_error_message   string
}

//基础信息类
type BaseRequest struct {
	Id string
}

/**
光口号认证请求类
 */
type UnifyAuthRequest struct {
	BaseRequest //匿名字段
	Loid               string
	Sn                 string
	Duration           string
	Fw_version         string
	Vendor             string
	Boot_flag          string
	Is_initialize      string
	Model              string
	Mac_address        string
	Connection_trigger string
	Last_infos         LastInfos //匿名字段
}
