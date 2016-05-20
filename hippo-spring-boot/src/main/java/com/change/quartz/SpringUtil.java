package com.change.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: change.long
 * Date: 16/5/20
 * Time: 下午1:41
 */
@Configuration
public class SpringUtil {

    static final SpringUtil springUtil = new SpringUtil();
    static ApplicationContext ctx;

    public static <T> T getBean(String name) {
        return (T) ctx.getBean(name);
    }

    @Bean
    public SpringUtil getCtx(ApplicationContext ctx) {
        SpringUtil.ctx = ctx;
        return springUtil;
    }

}
