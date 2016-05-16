package com.change.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午4:45
 */
@Component
public class HelloWorldService {

    @Value("${name:World}")
    private String name;

    public String getHelloMessage() {
        return "Hello " + this.name;
    }
}
