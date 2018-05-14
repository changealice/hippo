package main

import (
	"github.com/astaxie/beego/orm"
	_ "github.com/go-sql-driver/mysql" // 导入数据库驱动
	"fmt"
)

type User struct {
	Id   int64
	Name string `orm:"size(100)"`
}

func init() {
	//注册驱动
	orm.RegisterDriver("mysql", orm.DRMySQL)
	//设置默认数据库
	orm.RegisterDataBase("default", "mysql", "root@tcp(localhost:3306)/uap?charset=utf8", 30)
	//注册定义的model
	orm.RegisterModel(new(User))

	// 创建table
	//orm.RunSyncdb("default", false, true)
	orm.SetMaxIdleConns("default", 30)
	orm.SetMaxOpenConns("default", 30)
	orm.Debug = true
}
func main() {
	o := orm.NewOrm()

	//新增
	user := new(User)
	user.Name = "change"

	//声明 长度10000的用户
	var users [10000]User
	for index := 1; index < 10000; index++ {
		//新增
		user := User{Name: "change"}
		users[index] = user
	}

	id, e := o.InsertMulti(10000, users)
	fmt.Printf("row=%v,err=%v\n", id, e)

	//更新
	user.Name = "changejava"
	num, err := o.Update(user)
	fmt.Printf("num=%v,err=%v\n", num, err)

	//删除
	row, err := o.Delete(user)
	fmt.Printf("row=%v,err=%v\n", row, err)

	//读取
	readUser := new(User)
	readUser.Id = 4
	err = o.Read(readUser)

	if err == orm.ErrNoRows {
		fmt.Println("查询不到")
	} else if err == orm.ErrMissPK {
		fmt.Println("找不到主键")
	} else {
		fmt.Printf("id=%v,name=%v\n", readUser.Id, readUser.Name)
	}
}
