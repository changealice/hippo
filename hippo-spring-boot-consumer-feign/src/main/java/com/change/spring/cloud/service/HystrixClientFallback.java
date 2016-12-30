package com.change.spring.cloud.service;

import com.change.spring.cloud.entity.User;
import com.netflix.appinfo.InstanceInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/21
 * Time: 下午5:53
 */
@Component
public class HystrixClientFallback implements UserFeignClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

    @Override
    public User findById(Long id) {
        LOGGER.info("Feign findById 异常发生，进入fallback方法，接收的参数 id={}", id);
        User user = new User();
        user.setId(id);
        user.setUserName("Feign default username");
        user.setPassword("123");
        return user;
    }

    @Override
    public InstanceInfo instanceInfo() {
        LOGGER.info("Feign instanceInfo 异常发生，进入fallback方法");
        return null;
    }
}
