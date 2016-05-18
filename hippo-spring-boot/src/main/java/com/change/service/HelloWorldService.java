package com.change.service;

import com.change.domain.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午4:45
 */
@Component
public class HelloWorldService {

    @Value("${name:World}")
    private String name;


    @Resource(name = "appName")
    private String appName;

    public String getHelloMessage() {
        return "Hello " + this.name;
    }

    public String echoAppName() {
        return appName;
    }

    public void testSpringDevToolReloadClasses() {
        System.out.println("spring reload class" + this.getClass().getName());
    }

}
