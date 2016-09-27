package com.change;

import com.change.config.ImportConfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

/**
 * User: change.long Date:
 * 16/5/9 Time: 下午4:42
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.change")
@EnableScheduling
@Import(value = ImportConfig.class)
//@MapperScan("com.change.repository.mybatis")
public class SampleSimpleApplication implements CommandLineRunner {
    public static void main(String[] args) {
        ApplicationContext appCtx = SpringApplication.run(SampleSimpleApplication.class, args);
        String[] beanDefinitionNames = appCtx.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
//        for (String beanName : beanDefinitionNames) {
//            System.out.println(beanName);
//        }
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}
