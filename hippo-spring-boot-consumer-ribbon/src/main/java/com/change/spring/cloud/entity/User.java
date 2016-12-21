package com.change.spring.cloud.entity;

import java.io.Serializable;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/21
 * Time: 下午3:56
 */
public class User implements Serializable {


    private Long id;

    private String userName;

    private String password;

    /**
     * 账户标签，例如人民币账户，美元账户，二进制位
     */
    private String accountOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", accountOptions='" + accountOptions + '\'' +
                '}';
    }
}