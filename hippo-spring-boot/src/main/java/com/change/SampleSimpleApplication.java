package com.change;

import com.change.config.ImportConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * User: change.long Date:
 * 16/5/9 Time: 下午4:42
 */

@SpringBootApplication
@EnableScheduling
@Import(value = ImportConfig.class)
@EnableEurekaClient
@EnableHystrixDashboard
@EnableFeignClients
@EnableZuulProxy
@RibbonClient(name = "hippo-spring-boot", configuration = HippoConfiguration.class)
public class SampleSimpleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleSimpleApplication.class, args);
    }
}
