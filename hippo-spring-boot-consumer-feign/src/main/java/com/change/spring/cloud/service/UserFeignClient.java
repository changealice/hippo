package com.change.spring.cloud.service;

import com.change.spring.cloud.entity.User;
import com.netflix.appinfo.InstanceInfo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/21
 * Time: 下午2:57
 */
@FeignClient(name = "hippo-spring-boot", fallback = HystrixClientFallback.class)
public interface UserFeignClient {

    @RequestMapping("/users/{id}")
    User findById(@PathVariable("id") Long id);


    @RequestMapping("/eureka-instance")
    InstanceInfo instanceInfo();
}
