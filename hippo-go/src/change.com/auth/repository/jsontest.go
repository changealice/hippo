package main

import (
	"change.com/auth/domain"
	"encoding/json"
	"fmt"
)

func main() {
	var s domain.UnifyAuthResponse
	str := `{"code":"200000","connection_retry_interval":300,"id":"d7bd582b-94d9-4e38-b392-b3c9ed0fe16222","message":"Success"}`
	json.Unmarshal([]byte(str), &s)
	fmt.Println(s)

	bytes, err := json.Marshal(s)
	fmt.Printf("err=%v,result=%v\n", err, string(bytes))

}
