package com.change.hippo.sbdemo;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
//import com.change.hippo.sbdemo.properties.MyProperties1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;


@EnableSwagger2Doc
//@RestController
@SpringBootApplication
public class SbDemoApplication {


//    @Resource
//    private MyProperties1 myProperties1;

    public static void main(String[] args) {
        SpringApplication.run(SbDemoApplication.class, args);
    }

//    @GetMapping("/demo1")
//    public String demo1() {
//        return "Hello battcn";
//    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
            Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        };
    }

}
