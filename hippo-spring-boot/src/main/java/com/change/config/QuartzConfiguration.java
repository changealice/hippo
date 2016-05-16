package com.change.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * User: change.long
 * Date: 16/5/13
 * Time: 下午4:09
 */
@Configuration
@ComponentScan("com.change.quartz")
@EnableScheduling
public class QuartzConfiguration {


}
