package com.change.web;

import com.change.domain.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * User: change.long
 * Date: 16/5/16
 * Time: 下午7:27
 */
@RestController
public class HomeController {

    @Autowired
    private EurekaClient eurekaClient;


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;


    @ApiOperation(value = "首页", notes = "测试首页")
    @RequestMapping(value = "/", method = GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @ApiOperation(value = "全局异常测试", notes = "全局异常测试")
    @GetMapping(value = "/test_global_advice")
    public String exception() {
        new RuntimeException("error");
        return null;
    }

    @GetMapping(value = "eureka-instance")
    public InstanceInfo serviceUrl() {
        return eurekaClient.getNextServerFromEureka("HIPPO-SPRING-BOOT", true);
    }

    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        return this.discoveryClient.getLocalServiceInstance();
    }

    /**
     * load balance
     * @param id
     * @return
     */
    @GetMapping(value = "/lb/users/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id) {
        return restTemplate.getForEntity("http://HIPPO-SPRING-BOOT/users/" + id, User.class);
    }

}
