package com.change.spring.cloud.service;

import com.change.spring.cloud.entity.User;
import com.netflix.appinfo.InstanceInfo;

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

    @Autowired
    private RestTemplate restTemplate;

    public User findById(Long id) {
        return restTemplate.getForObject("http://hippo-spring-boot/users/" + id, User.class);
    }

    public InstanceInfo instanceInfo() {
        return restTemplate.getForObject("http://hippo-spring-boot/eureka-instance",InstanceInfo.class);
    }
}
