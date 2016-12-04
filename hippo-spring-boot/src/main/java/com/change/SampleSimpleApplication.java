package com.change;

import com.change.config.ImportConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
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
public class SampleSimpleApplication{
    public static void main(String[] args) {
        SpringApplication.run(SampleSimpleApplication.class, args);
    }
}
