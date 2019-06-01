package main

import (
	"database/sql"
	"fmt"
	"github.com/astaxie/beego/orm"
	_ "github.com/go-sql-driver/mysql"
	_ "github.com/mattn/go-sqlite3"
	"time"
)

// Model Struct
type SourceMapping struct {
	PWID       int64
	SysFrom    string
	AccountID  string
	CreateTime time.Time
	UpdateTime time.Time
}

func init() {
	// 设置默认数据库
	orm.RegisterDataBase("default", "mysql", "root@tcp(localhost:3306)/uap?charset=utf8", 30)

	// 注册定义的 model
	orm.RegisterModel(new(SourceMapping))
	//RegisterModel 也可以同时注册多个 model
	//orm.RegisterModel(new(User), new(Profile), new(Post))

	// 创建 table
	orm.RunSyncdb("default", false, true)
}
func main() {
	db, err := sql.Open("mysql", "root@tcp(localhost:3306)/uap?charset=utf8")
	checkErr(err)

	fmt.Println(db)

	stmt, err := db.Prepare("INSERT INTO uap.T_SourceMapping (PWID, SysFrom, AccountID, CreateTime, UpdateTime) VALUES(?, ?, ?, ?, ?)")
	checkErr(err)

	res, err := stmt.Exec(111111, "123", "sm", time.Now(), time.Now())
	checkErr(err)

	affect, err := res.RowsAffected()
	checkErr(err)

	fmt.Println(affect)

	rows, err := db.Query("SELECT * from uap.T_SourceMapping")

	for rows.Next() {
		var PWID int64
		var SysFrom string
		var AccountID string
		var CreateTime string
		var UpdateTime string
		err = rows.Scan(&PWID, &SysFrom, &AccountID, &CreateTime, &UpdateTime)
		checkErr(err)
		fmt.Println(PWID)
		fmt.Println(SysFrom)
		fmt.Println(AccountID)
		fmt.Println(CreateTime)
		fmt.Println(UpdateTime)
	}

	o := orm.NewOrm()

	// 读取 one
	u := SourceMapping{PWID: 111111}
	err = o.Read(&u)
	fmt.Printf("ERR: %v\n", err)

	//删除数据
	stmt, err = db.Prepare("delete  from uap.T_SourceMapping")
	checkErr(err)

	res, err = stmt.Exec()
	checkErr(err)

	affect, err = res.RowsAffected()
	checkErr(err)

	fmt.Println(affect)

	defer rows.Close()
	defer stmt.Close()
	defer db.Close()
}

func checkErr(err error) {
	if err != nil {
		panic(err)
	}
}
