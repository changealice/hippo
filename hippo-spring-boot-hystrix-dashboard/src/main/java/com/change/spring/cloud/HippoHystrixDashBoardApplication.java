package com.change.spring.cloud;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
public class HippoHystrixDashBoardApplication {


    public static void main(String[] args) {
        new SpringApplicationBuilder(HippoHystrixDashBoardApplication.class).web(true).run(args);
    }
}
