package com.change.springboot.autoconfigure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * User: change.long@99bill.com
 * Date: 2017/1/16
 * Time: 下午10:49
 * 快钱统一配置平台acms配置
 */
@Configuration
@EnableConfigurationProperties(AcmsProperties.class)
public class ConfigServerAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ConfigServerAutoConfiguration.class);

    private AcmsProperties acmsProperties;


    public ConfigServerAutoConfiguration(AcmsProperties acmsProperties) {
        this.acmsProperties = acmsProperties;
    }


}
