package com.change.spring.cloud.controller;

import com.change.spring.cloud.entity.User;
import com.change.spring.cloud.service.RibbonService;
import com.netflix.appinfo.InstanceInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/21
 * Time: 下午2:55
 */
@RestController
public class RibbonController {

    @Autowired
    private RibbonService ribbonService;

    @GetMapping("/ribbon/{id}")
    public User findById(@PathVariable("id") Long id) {
        return this.ribbonService.findById(id);
    }

    @GetMapping(value = "eureka-instance")
    public InstanceInfo serviceUrl() {
        return ribbonService.instanceInfo();
    }
}
