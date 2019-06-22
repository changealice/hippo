package com.change;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/4
 * Time: 下午7:36
 */
@Configuration
public class HippoConfiguration {

    @Bean
    public IRule ribbonRule() {
        return new RandomRule();
    }
}
