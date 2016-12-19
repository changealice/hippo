package com.change.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/15
 * Time: 下午12:11
 */
@Component
@ConfigurationProperties(prefix = "author")
public class AuthorSettings {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "AuthorSettings{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
