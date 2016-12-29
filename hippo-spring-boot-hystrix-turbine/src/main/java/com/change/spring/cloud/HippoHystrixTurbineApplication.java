package com.change.spring.cloud;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class HippoHystrixTurbineApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder(HippoHystrixTurbineApplication.class).web(true).run(args);
    }
}
