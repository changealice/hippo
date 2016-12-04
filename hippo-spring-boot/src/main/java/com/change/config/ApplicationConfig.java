package com.change.config;

import com.change.runnner.DiscoveryClientRunner;
import com.change.runnner.MyBatisCommandRunner;
import com.change.runnner.StartupCommandLineRunner;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * User: change.long Date: 16/5/14 Time: 上午12:16
 */
@Configuration
public class ApplicationConfig {


    @Bean
    public StartupCommandLineRunner startupRunner() {
        return new StartupCommandLineRunner();
    }


    @Bean
    public MyBatisCommandRunner myBatisCommandRunner() {
        return new MyBatisCommandRunner();
    }

    @Bean
    public DiscoveryClientRunner discoveryClientRunner() {
        return new DiscoveryClientRunner();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
