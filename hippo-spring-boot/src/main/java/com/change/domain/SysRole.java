package com.change.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/16
 * Time: 下午5:55
 */
@Entity
public class SysRole implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
