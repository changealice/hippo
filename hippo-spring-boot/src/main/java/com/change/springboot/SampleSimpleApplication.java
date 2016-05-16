package com.change.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

/**
 * User: change.long
 * Date: 16/5/9
 * Time: 下午4:42
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.change")
@EnableJpaRepositories(basePackages = "com.change.repository")
@EntityScan(basePackages = "com.change.domain")
@EnableScheduling
public class SampleSimpleApplication {
    public static void main(String[] args) {
        ApplicationContext appCtx = SpringApplication.run(SampleSimpleApplication.class, args);
        String[] beanDefinitionNames = appCtx.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
//        for (String beanName : beanDefinitionNames) {
//            System.out.println(beanName);
//        }
    }
}
