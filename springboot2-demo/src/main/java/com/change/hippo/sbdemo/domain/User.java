package com.change.hippo.sbdemo.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

@ApiModel
//@Entity
public class User {

    //    @Id
//    @GeneratedValue
    private Long id;

    @ApiModelProperty("用户名")
//    @Column(nullable = false)
    private String username;
    @ApiModelProperty("密码")
//    @Column(nullable = false)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
