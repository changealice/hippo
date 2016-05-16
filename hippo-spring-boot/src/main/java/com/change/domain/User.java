package com.change.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: change.long
 * Date: 16/5/13
 * Time: 下午3:51
 */
@Entity
@Table(name = "T_USER")
public class User implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

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
}
