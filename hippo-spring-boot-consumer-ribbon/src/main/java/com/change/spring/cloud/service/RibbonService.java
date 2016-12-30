package com.change.spring.cloud.service;

import com.change.spring.cloud.entity.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/21
 * Time: 下午2:57
 */
@Service
public class RibbonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RibbonService.class);

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用@HystrixCommand注解指定当该方法发生异常时调用的方法
     * @param id id
     * @return 通过id查询到的用户
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public User findById(Long id) {
        return restTemplate.getForObject("http://hippo-spring-boot/users/" + id, User.class);
    }

    public InstanceInfo instanceInfo() {
        return restTemplate.getForObject("http://hippo-spring-boot/eureka-instance", InstanceInfo.class);
    }

    public User fallback(Long id) {
        LOGGER.info("异常发生，进入fallback方法，接收的参数 id={}", id);
        User user = new User();
        user.setId(-1L);
        user.setUserName("default username");
        user.setPassword("123");
        return user;
    }
}
