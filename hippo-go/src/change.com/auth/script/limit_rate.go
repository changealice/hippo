package script

import (
	"github.com/astaxie/beego/logs"
	"io/ioutil"
	"os"
	"path/filepath"
)

var (
	pwd, _        = os.Getwd()
	scriptPath, _ = filepath.Abs("change.com/auth/script/limit.lua")
	script        string
)

func init() {
	//初始化lua脚本内容
	logs.Info("parse lua script")
	b, e := ioutil.ReadFile(scriptPath)
	if e != nil {
		logs.Error("load lua script path=%v failed", scriptPath)
	}
	script = string(b)

	logs.Info("load lua script successfully,path=%v,result=%v", scriptPath, script)
}
