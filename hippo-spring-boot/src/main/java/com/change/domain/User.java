package com.change.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

import javax.persistence.Cacheable;
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
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region = "com.change.domain.User")
public class User implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    /**
     * 账户标签，例如人民币账户，美元账户，二进制位
     */
    @Column(name = "ACCOUNT_OPTIONS")
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
}
