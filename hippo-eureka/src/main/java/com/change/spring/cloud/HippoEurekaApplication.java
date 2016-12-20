package com.change.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 使用Eureka做服务发现。
 */
@SpringBootApplication
@EnableEurekaServer
public class HippoEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HippoEurekaApplication.class, args);
    }
}
