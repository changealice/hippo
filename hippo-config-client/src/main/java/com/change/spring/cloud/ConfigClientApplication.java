package com.change.spring.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigClientApplication {


    @Value("${profile}")
    private String profile;

    @GetMapping("/profile")
    public String profile() {
        return profile;
    }


    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

}
