package com.change.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午5:28
 * 导入配置
 */
@Configuration
public class ImportConfig {


    @Bean
    public String appName(){
        return "spring-boot";
    }

}
