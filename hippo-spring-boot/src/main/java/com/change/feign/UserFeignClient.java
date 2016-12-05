package com.change.feign;

import com.change.domain.User;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/5
 * Time: 下午3:35
 */
@FeignClient("hippo-spring-boot")
public interface UserFeignClient {

    /**
     * feign balance
     */
    @RequestMapping(method = RequestMethod.GET, value = "/lb/users/{id}")
    public ResponseEntity<User> findOne(@PathVariable("id") Long id);

}
