package com.change.springboot.autoconfigure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import lombok.Data;

/**
 * User: change.long@99bill.com
 * Date: 2017/1/16
 * Time: 下午11:33
 * acms 配置项，读取classpath目录下*.properties文件
 */
@Data
@ConfigurationProperties(prefix = AcmsProperties.PREFIX)
public class AcmsProperties {
    static final String PREFIX = "spring.acms";
    private String appName;
    private boolean ignoreUnresolvablePlaceholders = true;
    private int order = Integer.MIN_VALUE;
    private String[] keyPatterns;
    private List<String> prefixStripKeys;

}