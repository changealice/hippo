package com.change.spring.cloud.service;

import com.change.spring.cloud.entity.User;
import com.netflix.appinfo.InstanceInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import feign.hystrix.FallbackFactory;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/21
 * Time: 下午2:57
 * fallback 与 fallbackFactory两者只能出现一个
 */
@FeignClient(name = "hippo-spring-boot",
//        fallback = HystrixClientFallback.class,
        fallbackFactory = UserFeignClient.UserFallbackFactory.class)
public interface UserFeignClient {

    @RequestMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);


    @RequestMapping("/eureka-instance")
    InstanceInfo instanceInfo();

    @Component
    class UserFallbackFactory implements FallbackFactory<UserFeignClient> {
        private static final Logger LOGGER = LoggerFactory.getLogger(UserFallbackFactory.class);

        @Override
        public UserFeignClient create(Throwable cause) {
            LOGGER.error("fallback;reason was={}", cause.getMessage());
            return new HystrixClientFallback() {
                @Override
                public User findById(Long id) {
                    return super.findById(id);
                }

                @Override
                public InstanceInfo instanceInfo() {
                    return super.instanceInfo();
                }
            };
        }
    }
}
